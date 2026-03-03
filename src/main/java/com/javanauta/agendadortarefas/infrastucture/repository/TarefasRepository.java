package com.javanauta.agendadortarefas.infrastucture.repository;

import com.javanauta.agendadortarefas.infrastucture.entity.TarefasEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefasRepository extends MongoRepository<TarefasEntity, String> {
}
