package com.nutriconecta.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.nutriconecta.demo.model.Receptor;

@Repository
public interface ReceptorRepository extends JpaRepository<Receptor, Long> {

    Receptor findByNome(String nomeReceptor);

} 
