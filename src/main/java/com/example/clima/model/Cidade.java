package com.example.clima.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{cidade.nome.notblank}")
    private String nome;

    @NotNull(message = "{cidade.latitude.notnull}")
    private Double latitude;

    @NotNull(message = "{cidade.longitude.notnull}")
    private Double longitude;

    @OneToMany(mappedBy = "cidade", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Clima> climas = new ArrayList<>();
}