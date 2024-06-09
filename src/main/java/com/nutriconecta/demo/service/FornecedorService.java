package com.nutriconecta.demo.service;

import com.nutriconecta.demo.model.Fornecedor;
import com.nutriconecta.demo.repository.FornecedorRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nutriconecta.demo.exception.InvalidFornecedorException;

@Service
public class FornecedorService {
    
    @Autowired
    private FornecedorRepository fornecedorRepository;

    public List<Fornecedor> getAllFornecedor() {
        return fornecedorRepository.findAll();
    }

    public Fornecedor getByIdFornecedor(Long id){
        return fornecedorRepository.findById(id)
            .orElseThrow(() -> new InvalidFornecedorException("O fornecedor não foi encontrado com id: " + id));
    }

    public Fornecedor getByName(String nomeFornecedor) {
        return fornecedorRepository.findByNome(nomeFornecedor);
    }

    public Fornecedor insert(Fornecedor fornecedor) {
        return fornecedorRepository.save(fornecedor);
    }

    public void deleteById(Long id) {
        if (!fornecedorRepository.existsById(id)) {
            throw new InvalidFornecedorException("O fornecedor não foi encontrado com id: " + id);
        }
        fornecedorRepository.deleteById(id);
    }

    public Fornecedor updateFornecedorById(Long id, Fornecedor fornecedorDetails) {
        Optional<Fornecedor> optionalFornecedor = fornecedorRepository.findById(id);
        if (optionalFornecedor.isEmpty()) {
            throw new InvalidFornecedorException("Fornecedor não foi encontrado com id: " + id);
        }

        validateFornecedor(fornecedorDetails);
        Fornecedor fornecedor = optionalFornecedor.get();

        fornecedor.setNome(fornecedorDetails.getNome());
        fornecedor.setEmail(fornecedorDetails.getEmail());
        fornecedor.setCnpj(fornecedorDetails.getCnpj());
        fornecedor.setTelefone(fornecedorDetails.getTelefone());
        fornecedor.setEndereco(fornecedorDetails.getEndereco());
        fornecedor.setDescricao(fornecedorDetails.getDescricao());

        Fornecedor updatedFornecedor = fornecedorRepository.save(fornecedor);
        return updatedFornecedor;
    }

    private void validateFornecedor(Fornecedor fornecedor) {
        if (fornecedor.getNome() == null || fornecedor.getNome().isEmpty() || fornecedor.getNome().length() > 50) {
            throw new InvalidFornecedorException("O nome do fornecedor não pode ser nulo, vazio ou ter mais de 50 caracteres");
        }
        if (fornecedor.getEmail() == null || fornecedor.getEmail().isEmpty() || fornecedor.getEmail().length() > 70) {
            throw new InvalidFornecedorException("O Email do fornecedor não pode ser nulo, vazio ou ter mais de 70 caracteres");
        }
        if (fornecedor.getCnpj() == null || fornecedor.getCnpj().isEmpty() || fornecedor.getCnpj().length() > 14) {
            throw new InvalidFornecedorException("O CNPJ do fornecedor não pode ser nulo, vazio ou ter mais de 14 caracteres");
        }
        if (fornecedor.getTelefone() == null || fornecedor.getTelefone().isEmpty() || fornecedor.getTelefone().length() > 12) {
            throw new InvalidFornecedorException("O Telefone do fornecedor não pode ser nulo, vazio ou ter mais de 12 caracteres");
        }
        if (fornecedor.getEndereco() == null || fornecedor.getEndereco().isEmpty() || fornecedor.getEndereco().length() > 80) {
            throw new InvalidFornecedorException("O Endereco do fornecedor não pode ser nulo, vazio ou ter mais de 80 caracteres");
        }
        if (fornecedor.getDescricao() == null || fornecedor.getDescricao().isEmpty() || fornecedor.getDescricao().length() > 800) {
            throw new InvalidFornecedorException("A Descricao do fornecedor não pode ser nulo, vazio ou ter mais de 800 caracteres");
        }
        
    }
}
