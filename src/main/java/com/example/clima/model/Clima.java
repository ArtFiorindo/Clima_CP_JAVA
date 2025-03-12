package com.example.clima.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Clima {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cidade_id")
    private Cidade cidade;

    private Double temperatura;

    private Double velocidadeVento;

    private String condicaoClimatica;

    private Double precipitacao;

    private LocalDateTime dataConsulta;


    public static Clima criarNovaConsulta(Cidade cidade, Double temperatura, Double velocidadeVento,
                                          String condicaoClimatica, Double precipitacao) {
        Clima clima = new Clima();
        clima.setCidade(cidade);
        clima.setTemperatura(temperatura);
        clima.setVelocidadeVento(velocidadeVento);
        clima.setCondicaoClimatica(condicaoClimatica);
        clima.setPrecipitacao(precipitacao);
        clima.setDataConsulta(LocalDateTime.now());
        return clima;
    }
}