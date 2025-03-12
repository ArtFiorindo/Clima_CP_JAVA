package com.example.clima.repository;

import com.example.clima.model.Cidade;
import com.example.clima.model.Clima;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClimaRepository extends JpaRepository<Clima, Long> {

    List<Clima> findByCidadeOrderByDataConsultaDesc(Cidade cidade);

    @Query("SELECT c FROM Clima c WHERE c.cidade.id = :cidadeId ORDER BY c.dataConsulta DESC")
    List<Clima> findLatestByCidade(Long cidadeId);

    Optional<Clima> findFirstByCidadeOrderByDataConsultaDesc(Cidade cidade);
}