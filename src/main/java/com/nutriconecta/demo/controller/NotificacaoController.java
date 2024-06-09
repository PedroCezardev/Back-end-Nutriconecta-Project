package com.nutriconecta.demo.controller;

import com.nutriconecta.demo.exception.InvalidNotificacaoException;
import com.nutriconecta.demo.service.NotificacaoService;
import com.nutriconecta.demo.model.Notificacao;

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
@RequestMapping(value = "/api/notificacao")
@AllArgsConstructor
public class NotificacaoController {

    @Autowired
    private NotificacaoService notificacaoService;

    @GetMapping("/teste")
    public ResponseEntity<String> teste() {
        return ResponseEntity.ok("Teste funcionando");
    }

    @GetMapping("/all")
    public List<Notificacao> getAllNotificacao() {
        return notificacaoService.getAllNotificacao();
    }

    @GetMapping("/{id}")
    public Notificacao getNotificacaoById(@PathVariable("id") Long id) {
        try {
            return notificacaoService.getByIdNotificacao(id);
        } catch (InvalidNotificacaoException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/add")
    public void insertReceptor(@RequestBody Notificacao notificacao) {
        notificacaoService.insert(notificacao);
    }

    @PutMapping("/{id}")
    public Notificacao updateNotificacao(@PathVariable Long id, @RequestBody Notificacao notificacaoDetails) {
        try {
            return notificacaoService.updateNotificacaoById(id, notificacaoDetails);
        } catch (InvalidNotificacaoException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteNotificacao(@PathVariable Long id) {
        try {
            notificacaoService.deleteById(id);
        } catch (InvalidNotificacaoException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}