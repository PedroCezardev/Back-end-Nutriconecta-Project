package com.nutriconecta.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.nutriconecta.demo.model.Divulgacao;

@Repository
public interface DivulgacaoRepository extends JpaRepository<Divulgacao, Long> {

    List<Divulgacao> findByNomePublicador(String nomePublicador);
    
} 