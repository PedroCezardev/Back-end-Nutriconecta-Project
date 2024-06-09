package com.nutriconecta.demo.service;

import com.nutriconecta.demo.model.Comentario;
import com.nutriconecta.demo.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nutriconecta.demo.exception.InvalidComentarioException;

import java.util.List;
import java.util.Optional;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    public List<Comentario> getAll() {
        return comentarioRepository.findAll();
    }

    public Comentario getById(Long id) {
        return comentarioRepository.findById(id)
                .orElseThrow(() -> new InvalidComentarioException("O comentário não foi encontrado com id: " + id));
    }

    public Comentario insert(Comentario comentario) {
        return comentarioRepository.save(comentario);
    }

    public void deleteById(Long id) {
        if (!comentarioRepository.existsById(id)) {
            throw new InvalidComentarioException("o comentário não foi encontrado com id: " + id);
        }
        comentarioRepository.deleteById(id);
    }

    public Comentario updateComentarioById(Long id, Comentario comentarioDetails) {
        Optional<Comentario> optionalComentario = comentarioRepository.findById(id);
        if (optionalComentario.isEmpty()) {
            throw new InvalidComentarioException("Comentário não encontrado com id: " + id);
        }

        validateComentario(comentarioDetails);
        Comentario comentario = optionalComentario.get();

        comentario.setDescricaoComentario(comentarioDetails.getDescricaoComentario());
        
        Comentario updatedComentario = comentarioRepository.save(comentario);
        return updatedComentario;
    }

    // algumas valicacoes para atualizar o comentário
    private void validateComentario(Comentario comentario) {
        if (comentario.getDescricaoComentario() == null || comentario.getDescricaoComentario().isEmpty() || comentario.getDescricaoComentario().length() > 2000) {
            throw new InvalidComentarioException("A descrição de um comentário não pode ser nulo, vazio ou ter mais de 50 caracteres");
        }
    }
}
