package com.nutriconecta.demo.controller;

import com.nutriconecta.demo.exception.InvalidConteudoException;
import com.nutriconecta.demo.service.ConteudoService;
import com.nutriconecta.demo.model.Conteudo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@RestController
@RequestMapping(value = "/api/conteudo")
@AllArgsConstructor
public class ConteudoController {

    @Autowired
    private ConteudoService conteudoService;

    @GetMapping("/teste")
    public ResponseEntity<String> teste() {
         return ResponseEntity.ok("Teste funcionando");
    }

    @GetMapping("/all")
    public List<Conteudo> getAllConteudos() {
         return conteudoService.getAll();
     }

    @GetMapping("/{id}")
    public Conteudo getConteudoById(@PathVariable("id") Long id) {
        try {
            return conteudoService.getById(id);
        } catch (InvalidConteudoException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/add")
    public Conteudo insertConteudo(@RequestBody Conteudo conteudo) {
        try {
            return conteudoService.insert(conteudo);
        } catch (InvalidConteudoException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Conteudo updateConteudo(@PathVariable Long id, @RequestBody Conteudo conteudoDetails) {
        try {
            return conteudoService.updateById(id, conteudoDetails);
        } catch (InvalidConteudoException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteConteudo(@PathVariable Long id) {
        try {
            conteudoService.deleteById(id);
        } catch (InvalidConteudoException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}