package com.nutriconecta.demo.service;

import com.nutriconecta.demo.model.Divulgacao;
import com.nutriconecta.demo.repository.DivulgacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nutriconecta.demo.exception.InvalidDivulgacaoException;

import java.util.List;
import java.util.Optional;

@Service
public class DivulgacaoService {

    @Autowired
    private DivulgacaoRepository divulgacaoRepository;

    public List<Divulgacao> getAllDivulgacao() {
        return divulgacaoRepository.findAll();
    }

    public Divulgacao getByIdDivulgacao(Long id) {
        if (id == null || id <= 0) {
            throw new InvalidDivulgacaoException("ID inválido: " + id);
        }
        return divulgacaoRepository.findById(id)
                .orElseThrow(() -> new InvalidDivulgacaoException("A divulgação não foi encontrada com id: " + id));
    }

    public List<Divulgacao> getByName(String nomePublicador) {
        return divulgacaoRepository.findByNomePublicador(nomePublicador);
    }

    public Divulgacao insert(Divulgacao divulgacao) {
        return divulgacaoRepository.save(divulgacao);
    }

    public void deleteById(Long id) {
        if (!divulgacaoRepository.existsById(id)) {
            throw new InvalidDivulgacaoException("A divulgacão não foi encontrado com id: " + id);
        }
        divulgacaoRepository.deleteById(id);
    }

    public Divulgacao updateDivulgacaoById(Long id, Divulgacao divulgacaoDetails) {
        Optional<Divulgacao> optionalDivulgacao = divulgacaoRepository.findById(id);
        if (optionalDivulgacao.isEmpty()) {
            throw new InvalidDivulgacaoException("Divulgacão não encontrado com id: " + id);
        }

        validateDivulgacao(divulgacaoDetails);
        Divulgacao divulgacao = optionalDivulgacao.get();

        divulgacao.setDescricaoDivulgacao(divulgacaoDetails.getDescricaoDivulgacao());
        
        Divulgacao updatedDivulgacao = divulgacaoRepository.save(divulgacao);
        return updatedDivulgacao;

    }

    // algumas valicacoes para atualizar um divulgacão
    private void validateDivulgacao(Divulgacao divulgacao) {
        if (divulgacao.getDescricaoDivulgacao() == null || divulgacao.getDescricaoDivulgacao().isEmpty() || divulgacao.getDescricaoDivulgacao().length() > 2000) {
            throw new InvalidDivulgacaoException("O nome do divulgacão não pode ser nulo, vazio ou ter mais de 2000 caracteres");
        }
    }
}
