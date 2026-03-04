package com.javanauta.agendadortarefas.business;

import com.javanauta.agendadortarefas.business.dto.TarefasDTO;
import com.javanauta.agendadortarefas.business.mapper.TarefasMapper;
import com.javanauta.agendadortarefas.business.mapper.TarefasUpdateMapper;
import com.javanauta.agendadortarefas.infrastucture.entity.TarefasEntity;
import com.javanauta.agendadortarefas.infrastucture.enums.StatusTarefa;
import com.javanauta.agendadortarefas.infrastucture.exceptions.ResourceNotFoundException;
import com.javanauta.agendadortarefas.infrastucture.repository.TarefasRepository;
import com.javanauta.agendadortarefas.infrastucture.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasRepository tarefasRepository;
    private final TarefasMapper tarefasMapper;
    private final JwtUtil jwtUtil;
    private final TarefasUpdateMapper tarefasUpdateMapper;

    public TarefasDTO gravaTarefas(String token, TarefasDTO dto) {
        String email = jwtUtil.extractUsername(token.substring(7));

        dto.setDataCriacao(LocalDateTime.now());
        dto.setStatusTarefa(StatusTarefa.PENDENTE);
        dto.setEmailUsuario(email);
        TarefasEntity entity = tarefasMapper.paraTarefasEntity(dto);

        return tarefasMapper.paraTarefasDTO(
                tarefasRepository.save(entity));
    }

    public List<TarefasDTO> buscarTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) {
        return tarefasMapper.paraListaTarefasDTO(
                tarefasRepository.findByDataEventoBetween(dataInicial, dataFinal));
    }

    public List<TarefasDTO> buscarTarefasPorEmail(String token) {
        String email = jwtUtil.extractUsername(token.substring(7));
        return tarefasMapper.paraListaTarefasDTO(tarefasRepository.findByEmailUsuario(email));
    }

    public void deletarTarefasPorId(String id) {

        try {
            tarefasRepository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao deletar tarefa por id, id inexistente " + id,
                    e.getCause());
        }
    }

    public TarefasDTO alterarStatusNotificacao(StatusTarefa statusTarefa, String id) {
        try {
            TarefasEntity entity = tarefasRepository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException("Tarefa não encontrada " + id));
            entity.setStatusTarefa(statusTarefa);
            return tarefasMapper.paraTarefasDTO(tarefasRepository.save(entity));
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException("Erro ao alterar status da tarefa " + e.getCause());
        }
    }

    public TarefasDTO updateTarefas(TarefasDTO dto, String id) {
        try {
            TarefasEntity entity = tarefasRepository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException("Tarefa não encontrada " + id));
            tarefasUpdateMapper.updateTarefas(dto, entity);
            return tarefasMapper.paraTarefasDTO(tarefasRepository.save(entity));

        } catch (ResourceNotFoundException e) {
            throw new RuntimeException("Erro ao alterar tarefa " + e.getCause());
        }
    }
}
