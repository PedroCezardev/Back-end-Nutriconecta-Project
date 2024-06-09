package com.nutriconecta.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nutriconecta.demo.model.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

} 