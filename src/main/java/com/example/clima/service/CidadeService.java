package com.example.clima.service;

import com.example.clima.model.Cidade;
import com.example.clima.repository.CidadeRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Validated
public class CidadeService {

    private final CidadeRepository cidadeRepository;

    public List<Cidade> listarTodas() {
        return cidadeRepository.findAll();
    }

    public Optional<Cidade> buscarPorId(Long id) {
        return cidadeRepository.findById(id);
    }

    @Transactional
    public Cidade salvar(@Valid Cidade cidade) {
        return cidadeRepository.save(cidade);
    }

    @Transactional
    public void excluir(Long id) {
        cidadeRepository.deleteById(id);
    }
}