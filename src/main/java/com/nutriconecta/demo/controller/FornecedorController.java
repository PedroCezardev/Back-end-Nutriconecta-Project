package com.nutriconecta.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nutriconecta.demo.exception.InvalidFornecedorException;
import com.nutriconecta.demo.service.FornecedorService;
import com.nutriconecta.demo.model.Fornecedor;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(value = "/api/fornecedor")
public class FornecedorController {
    
    @Autowired
    private FornecedorService fornecedorService;

    @GetMapping("/teste")
    public ResponseEntity<String> teste() {
        return ResponseEntity.ok("Teste funcionando");
    }

    @PostMapping("/add")
    public Fornecedor criarFornecedor(@RequestBody Fornecedor fornecedor){
        return fornecedorService.insert(fornecedor);
    }

    @GetMapping("/all")
    public List<Fornecedor> listarTodosFornecedores(){
        return fornecedorService.getAllFornecedor();
    }

    @GetMapping("/{id}")
    public Fornecedor buscarFornecedorPorId(@PathVariable Long id){
        try {
            return fornecedorService.getByIdFornecedor(id);
        } catch (InvalidFornecedorException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/nome/{nomeFornecedor}")
    public Fornecedor buscarFornecedorPorNome(@PathVariable String nomeFornecedor) {
        return fornecedorService.getByName(nomeFornecedor);
    }

    @PutMapping("/{id}")
    public Fornecedor atualizarFornecedor(@PathVariable Long id, @RequestBody Fornecedor fornecedorDetails) {
        try {
            return fornecedorService.updateFornecedorById(id, fornecedorDetails);
        } catch (InvalidFornecedorException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deletarFornecedor(@PathVariable Long id){
        try{
            fornecedorService.deleteById(id);
        } catch (InvalidFornecedorException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
