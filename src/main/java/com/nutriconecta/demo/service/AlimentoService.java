package com.nutriconecta.demo.service;

import com.nutriconecta.demo.model.Alimento;
import com.nutriconecta.demo.repository.AlimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nutriconecta.demo.exception.InvalidAlimentoException;

import java.util.List;
import java.util.Optional;

@Service
public class AlimentoService {

    @Autowired
    private AlimentoRepository alimentoRepository;

    public List<Alimento> getAll() {
        return alimentoRepository.findAll();
    }

    public Alimento getById(Long id) {
        return alimentoRepository.findById(id)
                .orElseThrow(() -> new InvalidAlimentoException("O alimento não foi encontrado com id: " + id));
    }

    public List<Alimento> getByName(String nomeAlimento) {
        return alimentoRepository.findByNomeAlimento(nomeAlimento);
    }

    public Alimento insert(Alimento alimento) {
        return alimentoRepository.save(alimento);
    }

    public void deleteById(Long id) {
        if (!alimentoRepository.existsById(id)) {
            throw new InvalidAlimentoException("o alimento não foi encontrado com id: " + id);
        }
        alimentoRepository.deleteById(id);
    }

    public Alimento updateAlimentoById(Long id, Alimento alimentoDetails) {
        Optional<Alimento> optionalAlimento = alimentoRepository.findById(id);
        if (optionalAlimento.isEmpty()) {
            throw new InvalidAlimentoException("Alimento não encontrado com id: " + id);
        }

        validateAlimento(alimentoDetails);
        Alimento alimento = optionalAlimento.get();

        alimento.setNomeAlimento(alimentoDetails.getNomeAlimento());
        alimento.setTipoAlimento(alimentoDetails.getTipoAlimento());
        alimento.setQuantidadeAlimento(alimentoDetails.getQuantidadeAlimento());
        
        Alimento updatedAlimento = alimentoRepository.save(alimento);
        return updatedAlimento;
    }

    // algumas valicacoes para atualizar um alimento
    private void validateAlimento(Alimento alimento) {
        if (alimento.getNomeAlimento() == null || alimento.getNomeAlimento().isEmpty() || alimento.getNomeAlimento().length() > 50) {
            throw new InvalidAlimentoException("O nome do alimento não pode ser nulo, vazio ou ter mais de 50 caracteres");
        }
        if (alimento.getTipoAlimento() == null || alimento.getTipoAlimento().isEmpty() || alimento.getTipoAlimento().length() > 50) {
            throw new InvalidAlimentoException("O Tipo do alimento não pode ser nulo, vazio ou ter mais de 50 caracteres");
        }
        if (alimento.getQuantidadeAlimento() <= 0) {
            throw new InvalidAlimentoException("A quantidade do alimento deve ser maior que zero");
        }
    }
}