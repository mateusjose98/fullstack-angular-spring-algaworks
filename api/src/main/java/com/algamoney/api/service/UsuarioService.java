package com.algamoney.api.service;

import com.algamoney.api.model.Usuario;
import com.algamoney.api.repository.UsuarioRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2

public class UsuarioService {

    final UsuarioRepository usuarioRepository;
    final PasswordEncoder passwordEncoder;

    public Usuario findByLogin(String login) {
        return usuarioRepository.findByEmail(login)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado " + login));
    }

    public Usuario findByID(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
    @Transactional
    public void changePassword(String username, String oldPassword, String newPassword) {
        Usuario usuario = findByLogin(username);
        if(!passwordEncoder.matches(oldPassword, usuario.getSenha())) {
           throw new RuntimeException("Antiga senha não corresponde!");
        }
        usuario.updatePassword(passwordEncoder, newPassword);
    }

}
