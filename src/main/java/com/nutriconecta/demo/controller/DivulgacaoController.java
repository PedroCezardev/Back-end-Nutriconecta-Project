package com.nutriconecta.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nutriconecta.demo.exception.InvalidDivulgacaoException;
import com.nutriconecta.demo.service.DivulgacaoService;
import com.nutriconecta.demo.model.Divulgacao;
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
@RequestMapping(value="/api/divulgacao")
public class DivulgacaoController{

    @Autowired
    private DivulgacaoService divulgacaoService;

    @GetMapping("/teste")
    public ResponseEntity<String> teste() {
        return ResponseEntity.ok("Teste funcionando");
    }

    @PostMapping("/add")
    public Divulgacao criarDivulgacao(@RequestBody Divulgacao divulgacao){
        return divulgacaoService.insert(divulgacao);
    }

    @GetMapping("/all")
    public List<Divulgacao> listarTodasDivulgacoes(){
        return divulgacaoService.getAllDivulgacao();
    }

    @GetMapping("/{id}")
    public Divulgacao buscarDivulgacaoPorId(@PathVariable Long id){
        try {
            return divulgacaoService.getByIdDivulgacao(id);
        } catch (InvalidDivulgacaoException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/nome/{nome}")
    public List<Divulgacao> buscarDivulgacaoPorNome(@PathVariable String nome) {
        return divulgacaoService.getByName(nome);
    }

    @PutMapping("/{id}")
    public Divulgacao atualizarDivulgacao(@PathVariable Long id, @RequestBody Divulgacao divulgacaoDetails) {
        try {
            return divulgacaoService.updateDivulgacaoById(id, divulgacaoDetails);
        } catch (InvalidDivulgacaoException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deletarDivulgacao(@PathVariable Long id){
        try{
            divulgacaoService.deleteById(id);
        } catch (InvalidDivulgacaoException e) {
            throw new RuntimeException(e.getMessage());
        }    
    }
}