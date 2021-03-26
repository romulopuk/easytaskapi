package com.romulomotta.repository;

import com.romulomotta.model.Tarefa;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarefasRepository extends MongoRepository<Tarefa, String> {
    List<Tarefa> findByDescricaoLikeIgnoreCase(String descricao);
}
