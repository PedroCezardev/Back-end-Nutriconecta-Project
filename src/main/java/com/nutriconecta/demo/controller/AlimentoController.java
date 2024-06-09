package com.nutriconecta.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nutriconecta.demo.exception.InvalidAlimentoException;
import com.nutriconecta.demo.service.AlimentoService;
import com.nutriconecta.demo.model.Alimento;
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
@RequestMapping(value="/api/alimento")
public class AlimentoController{

    @Autowired
    private AlimentoService alimentoService;

    @GetMapping("/teste")
    public ResponseEntity<String> teste() {
        return ResponseEntity.ok("Teste funcionando");
    }

    @PostMapping("/add")
    public Alimento criarAlimento(@RequestBody Alimento alimento){
        return alimentoService.insert(alimento);
    }

    @GetMapping("/all")
    public List<Alimento> listarTodosAlimentos(){
        return alimentoService.getAll();
    }

    @GetMapping("/{id}")
    public Alimento buscarAlimentoPorId(@PathVariable Long id){
        try {
            return alimentoService.getById(id);
        } catch (InvalidAlimentoException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/nome/{nome}")
    public List<Alimento> buscarAlimentoPorNome(@PathVariable String nome) {
        return alimentoService.getByName(nome);
    }

    @PutMapping("/{id}")
    public Alimento atualizarAlimento(@PathVariable Long id, @RequestBody Alimento alimentoDetails) {
        try {
            return alimentoService.updateAlimentoById(id, alimentoDetails);
        } catch (InvalidAlimentoException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deletarAlimento(@PathVariable Long id){
        try{
            alimentoService.deleteById(id);
        } catch (InvalidAlimentoException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}