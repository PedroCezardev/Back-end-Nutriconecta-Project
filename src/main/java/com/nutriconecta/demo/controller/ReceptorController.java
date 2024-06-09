package com.nutriconecta.demo.controller;

import com.nutriconecta.demo.exception.InvalidReceptorException;
import com.nutriconecta.demo.service.ReceptorService;
import com.nutriconecta.demo.model.Receptor;

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
@RequestMapping(value = "/api/receptor")
@AllArgsConstructor
public class ReceptorController {

    @Autowired
    private ReceptorService receptorService;

    @GetMapping("/teste")
    public ResponseEntity<String> teste() {
        return ResponseEntity.ok("Teste funcionando");
    }

    @GetMapping("/all")
    public List<Receptor> getAllReceptores() {
        return receptorService.getAllReceptores();
    }

    @GetMapping("/{id}")
    public Receptor getReceptorById(@PathVariable("id") Long id) {
        try {
            return receptorService.getReceptorById(id);
        } catch (InvalidReceptorException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/nome/{nomeReceptor}")
    public Receptor buscarReceptorPorNome(@PathVariable String nomeReceptor) {
        return receptorService.getByName(nomeReceptor);
    }

    @PostMapping("/add")
    public void insertReceptor(@RequestBody Receptor receptor) {
        receptorService.insertReceptor(receptor);
    }

    @PutMapping("/{id}")
    public Receptor updateReceptor(@PathVariable Long id, @RequestBody Receptor receptorDetails) {
        try {
            return receptorService.updateReceptorById(id, receptorDetails);
        } catch (InvalidReceptorException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteReceptor(@PathVariable Long id) {
        try {
            receptorService.deleteReceptorById(id);
        } catch (InvalidReceptorException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}