package com.nutriconecta.demo.service;

import com.nutriconecta.demo.model.Notificacao;
import com.nutriconecta.demo.repository.NotificacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nutriconecta.demo.exception.InvalidNotificacaoException;

import java.util.List;
import java.util.Optional;

@Service
public class NotificacaoService {

    @Autowired
    private NotificacaoRepository notificacaoRepository;

    public List<Notificacao> getAllNotificacao() {
        return notificacaoRepository.findAll();
    }

    public Notificacao getByIdNotificacao(Long id) {
        return notificacaoRepository.findById(id)
                .orElseThrow(() -> new InvalidNotificacaoException("A Notificacao não foi encontrado com id: " + id));
    }

    public Notificacao insert(Notificacao notificacao) {
        return notificacaoRepository.save(notificacao);
    }

    public void deleteById(Long id) {
        if (!notificacaoRepository.existsById(id)) {
            throw new InvalidNotificacaoException("A Notificacao não foi encontrado com id: " + id);
        }
        notificacaoRepository.deleteById(id);
    }

    public Notificacao updateNotificacaoById(Long id, Notificacao notificacaoDetails) {
        Optional<Notificacao> optionalNotificacao = notificacaoRepository.findById(id);
        if (optionalNotificacao.isEmpty()) {
            throw new InvalidNotificacaoException("Notificacao não encontrado com id: " + id);
        }

        validateNotificacao(notificacaoDetails);
        Notificacao notificacao = optionalNotificacao.get();

        notificacao.setDescricaoNotificacao(notificacaoDetails.getDescricaoNotificacao());
        
        Notificacao updatedNotificacao = notificacaoRepository.save(notificacao);
        return updatedNotificacao;
    }

    // algumas valicacoes para atualizar o comentário
    private void validateNotificacao(Notificacao notificacao) {
        if (notificacao.getDescricaoNotificacao() == null || notificacao.getDescricaoNotificacao().isEmpty() || notificacao.getDescricaoNotificacao().length() > 2000) {
            throw new InvalidNotificacaoException("A Notificacao de um comentário não pode ser nulo, vazio ou ter mais de 50 caracteres");
        }
    }
}
