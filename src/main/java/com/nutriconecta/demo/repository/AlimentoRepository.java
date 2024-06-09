package com.nutriconecta.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.nutriconecta.demo.model.Alimento;

@Repository
public interface AlimentoRepository extends JpaRepository<Alimento, Long> {

    List<Alimento> findByNomeAlimento(String nomeAlimento);
    
} 
