package com.example.clima.controller;

import com.example.clima.model.Cidade;
import com.example.clima.service.CidadeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/cidades")
@RequiredArgsConstructor
public class CidadeController {

    private final CidadeService cidadeService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("cidades", cidadeService.listarTodas());
        return "cidade/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("cidade", new Cidade());
        return "cidade/form";
    }

    @PostMapping
    public String salvar(@Valid @ModelAttribute("cidade") Cidade cidade,
                         BindingResult result,
                         RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "cidade/form";
        }

        cidadeService.salvar(cidade);
        redirectAttributes.addFlashAttribute("mensagem", "Cidade salva com sucesso!");
        return "redirect:/admin/cidades";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("cidade", cidadeService.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Cidade inválida")));
        return "cidade/form";
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        cidadeService.excluir(id);
        redirectAttributes.addFlashAttribute("mensagem", "Cidade excluída com sucesso!");
        return "redirect:/admin/cidades";
    }
}
