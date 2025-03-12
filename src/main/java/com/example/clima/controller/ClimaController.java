package com.example.clima.controller;

import com.example.clima.model.Cidade;
import com.example.clima.model.Clima;
import com.example.clima.service.CidadeService;
import com.example.clima.service.ClimaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/clima")
@RequiredArgsConstructor
public class ClimaController {

    private final ClimaService climaService;
    private final CidadeService cidadeService;

    @GetMapping("/consulta")
    public String consultaForm(Model model) {
        List<Cidade> cidades = cidadeService.listarTodas();
        model.addAttribute("cidades", cidades);

        // Buscar o clima mais recente para cada cidade
        Map<Long, Clima> ultimasConsultas = new HashMap<>();
        for (Cidade cidade : cidades) {
            Optional<Clima> ultimaConsulta = climaService.buscarUltimoPorCidade(cidade);
            ultimaConsulta.ifPresent(clima -> ultimasConsultas.put(cidade.getId(), clima));
        }

        model.addAttribute("ultimasConsultas", ultimasConsultas);
        return "clima/consulta";
    }

    @PostMapping("/consultar")
    public String consultar(@RequestParam Long cidadeId, RedirectAttributes redirectAttributes) {
        try {
            Clima novoClima = climaService.consultarEArmazenarClima(cidadeId);
            redirectAttributes.addFlashAttribute("mensagem", "Clima consultado com sucesso!");
            redirectAttributes.addFlashAttribute("clima", novoClima);
            redirectAttributes.addFlashAttribute("cidadeId", cidadeId);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao consultar clima: " + e.getMessage());
        }

        return "redirect:/clima/consulta";
    }

    @GetMapping("/historico/{cidadeId}")
    public String historico(@PathVariable Long cidadeId, Model model) {
        Cidade cidade = cidadeService.buscarPorId(cidadeId)
                .orElseThrow(() -> new IllegalArgumentException("Cidade não encontrada"));

        List<Clima> historicoClima = climaService.listarTodosPorCidade(cidade);
        model.addAttribute("cidade", cidade);
        model.addAttribute("historico", historicoClima);

        return "clima/historico";
    }
}