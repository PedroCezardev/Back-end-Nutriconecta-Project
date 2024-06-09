package com.nutriconecta.demo.service;

import com.nutriconecta.demo.exception.InvalidFornecedorException;
import com.nutriconecta.demo.exception.InvalidReceptorException;
import com.nutriconecta.demo.model.Receptor;
import com.nutriconecta.demo.repository.ReceptorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReceptorService {

    @Autowired
    private ReceptorRepository receptorRepository;

    public List<Receptor> getAllReceptores() {
        return receptorRepository.findAll();
    }

    public Receptor getReceptorById(Long id) {
        return receptorRepository.findById(id)
                .orElseThrow(() -> new InvalidReceptorException("Receptor não encontrado com id: " + id));
    }

    public Receptor getByName(String nomeReceptor) {
        return receptorRepository.findByNome(nomeReceptor);
    }

    public Receptor insertReceptor(Receptor receptor) {
        validateReceptor(receptor);
        return receptorRepository.save(receptor);
    }

    public void deleteReceptorById(Long id) {
        if (!receptorRepository.existsById(id)) {
            throw new InvalidReceptorException("Receptor não encontrado com id: " + id);
        }
        receptorRepository.deleteById(id);
    }

    public Receptor updateReceptorById(Long id, Receptor receptorDetails) {
        Optional<Receptor> optionalReceptor = receptorRepository.findById(id);
        if (optionalReceptor.isEmpty()) {
            throw new InvalidReceptorException("Conteudo não encontrado com id: " + id);
        }

        validateReceptor(receptorDetails);
        Receptor receptor = optionalReceptor.get();

        receptor.setNome(receptorDetails.getNome());
        receptor.setEmail(receptorDetails.getEmail());
        receptor.setCnpj(receptorDetails.getCnpj());
        receptor.setTelefone(receptorDetails.getTelefone());
        receptor.setEndereco(receptorDetails.getEndereco());
        receptor.setDescricao(receptorDetails.getDescricao());

        Receptor updatedReceptor = receptorRepository.save(receptor);
        return updatedReceptor;
    }

    private void validateReceptor(Receptor receptor) {
        if (receptor.getNome() == null || receptor.getNome().isEmpty() || receptor.getNome().length() > 50) {
            throw new InvalidFornecedorException("O nome do receptor não pode ser nulo, vazio ou ter mais de 50 caracteres");
        }
        if (receptor.getEmail() == null || receptor.getEmail().isEmpty() || receptor.getEmail().length() > 70) {
            throw new InvalidFornecedorException("O Email do receptor não pode ser nulo, vazio ou ter mais de 70 caracteres");
        }
        if (receptor.getCnpj() == null || receptor.getCnpj().isEmpty() || receptor.getCnpj().length() > 14) {
            throw new InvalidFornecedorException("O CNPJ do receptor não pode ser nulo, vazio ou ter mais de 14 caracteres");
        }
        if (receptor.getTelefone() == null || receptor.getTelefone().isEmpty() || receptor.getTelefone().length() > 12) {
            throw new InvalidFornecedorException("O Telefone do receptor não pode ser nulo, vazio ou ter mais de 12 caracteres");
        }
        if (receptor.getEndereco() == null || receptor.getEndereco().isEmpty() || receptor.getEndereco().length() > 80) {
            throw new InvalidFornecedorException("O Endereco do receptor não pode ser nulo, vazio ou ter mais de 80 caracteres");
        }
        if (receptor.getDescricao() == null || receptor.getDescricao().isEmpty() || receptor.getDescricao().length() > 800) {
            throw new InvalidFornecedorException("A Descricao do receptor não pode ser nulo, vazio ou ter mais de 800 caracteres");
        }
    }
}