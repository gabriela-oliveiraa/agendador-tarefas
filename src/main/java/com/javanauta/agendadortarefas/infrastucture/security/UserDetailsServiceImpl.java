package com.javanauta.agendadortarefas.infrastucture.security;

import com.javanauta.agendadortarefas.business.dto.UsuarioDTO;
import com.javanauta.agendadortarefas.infrastucture.client.UsuarioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl{

    @Autowired
    private UsuarioClient usuarioClient;

    public UserDetails carregaDadosUsuario(String email, String token){

        UsuarioDTO usuarioDTO = usuarioClient.buscarUsuarioPorEmail(email, token);
        return User
                .withUsername(usuarioDTO.getEmail())
                .password(usuarioDTO.getSenha())
                .build();
    }
}
