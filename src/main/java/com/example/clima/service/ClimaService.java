package com.example.clima.service;

import com.example.clima.client.OpenMeteoClient;
import com.example.clima.dto.OpenMeteoResponse;
import com.example.clima.exception.ClimaConsultaException;
import com.example.clima.model.Cidade;
import com.example.clima.model.Clima;
import com.example.clima.repository.ClimaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClimaService {

    private final ClimaRepository climaRepository;
    private final OpenMeteoClient openMeteoClient;
    private final CidadeService cidadeService;
    private final MessageSource messageSource;

    public List<Clima> listarTodosPorCidade(Cidade cidade) {
        return climaRepository.findByCidadeOrderByDataConsultaDesc(cidade);
    }

    public Optional<Clima> buscarUltimoPorCidade(Cidade cidade) {
        return climaRepository.findFirstByCidadeOrderByDataConsultaDesc(cidade);
    }

    @Transactional
    public Clima consultarEArmazenarClima(Long cidadeId) {
        Cidade cidade = cidadeService.buscarPorId(cidadeId)
                .orElseThrow(() -> new IllegalArgumentException(
                        messageSource.getMessage("erro.cidade.nao.encontrada", null, LocaleContextHolder.getLocale())
                ));

        try {
            OpenMeteoResponse response = openMeteoClient.getWeatherData(cidade.getLatitude(), cidade.getLongitude());

            Clima clima = new Clima();
            clima.setCidade(cidade);
            clima.setTemperatura(response.getCurrent().getTemperature());
            clima.setVelocidadeVento(response.getCurrent().getWindSpeed());
            clima.setCondicaoClimatica(response.getCurrent().getWeatherDescription());
            clima.setPrecipitacao(response.getCurrent().getPrecipitation());
            clima.setDataConsulta(LocalDateTime.now());

            return climaRepository.save(clima);
        } catch (RestClientException e) {
            throw new ClimaConsultaException(
                    messageSource.getMessage("erro.consulta.clima", null, LocaleContextHolder.getLocale()),
                    e
            );
        }
    }

    @Transactional
    public void salvar(Clima clima) {
        climaRepository.save(clima);
    }
}