package com.javanauta.agendadortarefas.infrastucture.repository;

import com.javanauta.agendadortarefas.infrastucture.entity.TarefasEntity;
import com.javanauta.agendadortarefas.infrastucture.enums.StatusTarefa;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TarefasRepository extends MongoRepository<TarefasEntity, String> {

    List<TarefasEntity> findByDataEventoBetweenAndStatusTarefa(LocalDateTime dataInicial,
                                                               LocalDateTime dataFinal,
                                                               StatusTarefa status);
    List<TarefasEntity> findByEmailUsuario(String email);
}
