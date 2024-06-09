package com.nutriconecta.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nutriconecta.demo.exception.InvalidComentarioException;
import com.nutriconecta.demo.service.ComentarioService;
import com.nutriconecta.demo.model.Comentario;
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
@RequestMapping(value="/api/comentario")
public class ComentarioController{

    @Autowired
    private ComentarioService comentarioService;

    @GetMapping("/teste")
    public ResponseEntity<String> teste() {
        return ResponseEntity.ok("Teste funcionando");
    }

    @PostMapping("/add")
    public Comentario criarComentario(@RequestBody Comentario comentario){
        return comentarioService.insert(comentario);
    }

    @GetMapping("/all")
    public List<Comentario> listarTodosComentarios(){
        return comentarioService.getAll();
    }

    @GetMapping("/{id}")
    public Comentario buscarComentarioPorId(@PathVariable Long id){
        try {
            return comentarioService.getById(id);
        } catch (InvalidComentarioException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Comentario atualizarComentario(@PathVariable Long id, @RequestBody Comentario comentarioDetails) {
        try {
            return comentarioService.updateComentarioById(id, comentarioDetails);
        } catch (InvalidComentarioException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deletarComentario(@PathVariable Long id){
        try{
            comentarioService.deleteById(id);
        } catch (InvalidComentarioException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}