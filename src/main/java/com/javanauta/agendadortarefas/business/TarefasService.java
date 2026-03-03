package com.javanauta.agendadortarefas.business;

import com.javanauta.agendadortarefas.business.dto.TarefasDTO;
import com.javanauta.agendadortarefas.business.mapper.TarefasMapper;
import com.javanauta.agendadortarefas.infrastucture.entity.TarefasEntity;
import com.javanauta.agendadortarefas.infrastucture.enums.StatusTarefa;
import com.javanauta.agendadortarefas.infrastucture.repository.TarefasRepository;
import com.javanauta.agendadortarefas.infrastucture.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasRepository tarefasRepository;
    private final TarefasMapper tarefasMapper;
    private final JwtUtil jwtUtil;

    public TarefasDTO gravaTarefas(String token, TarefasDTO dto) {
        String email = jwtUtil.extractUsername(token.substring(7));

        dto.setDataCriacao(LocalDateTime.now());
        dto.setStatusTarefa(StatusTarefa.PENDENTE);
        dto.setEmailUsuario(email);
        TarefasEntity entity = tarefasMapper.paraTarefasEntity(dto);

        return tarefasMapper.paraTarefasDTO(
                tarefasRepository.save(entity));
    }
}
