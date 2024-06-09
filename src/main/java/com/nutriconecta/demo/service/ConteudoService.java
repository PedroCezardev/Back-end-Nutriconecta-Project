package com.nutriconecta.demo.service;

import com.nutriconecta.demo.exception.InvalidConteudoException;
import com.nutriconecta.demo.model.Conteudo;
import com.nutriconecta.demo.repository.ConteudoRepository;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConteudoService {

    @Autowired
    private ConteudoRepository conteudoRepository;

    public List<Conteudo> getAll() {
        return conteudoRepository.findAll();
    }

    public Conteudo getById(Long id) {
        return conteudoRepository.findById(id)
                .orElseThrow(() -> new InvalidConteudoException("Conteudo não encontrado com id: " + id));
    }

    public Conteudo insert(Conteudo conteudo) {
        return conteudoRepository.save(conteudo);
    }

    public void deleteById(Long id) {
        if (!conteudoRepository.existsById(id)) {
            throw new InvalidConteudoException("Conteudo não encontrado com id: " + id);
        }
        conteudoRepository.deleteById(id);
    }

    public Conteudo updateById(Long id, Conteudo conteudoDetails) {
        Optional<Conteudo> optionalConteudo = conteudoRepository.findById(id);
        if (optionalConteudo.isEmpty()) {
            throw new InvalidConteudoException("Conteudo não encontrado com id: " + id);

        }

        validateConteudo(conteudoDetails);
        Conteudo conteudo = optionalConteudo.get();

        conteudo.setDescricaoConteudo(conteudoDetails.getDescricaoConteudo());

        return conteudoRepository.save(conteudo);
    }

    private void validateConteudo(Conteudo conteudo) {
        if (conteudo.getDescricaoConteudo() == null || conteudo.getDescricaoConteudo().isEmpty()) {
            throw new InvalidConteudoException("A descrição do conteúdo não pode ser nula ou vazia");
        }
        if (conteudo.getDescricaoConteudo().length() > 2000) {
            throw new InvalidConteudoException("A descrição do conteúdo não pode ter mais de 2000 caracteres");
        }
    }
}