package com.romulomotta.controller;

import com.romulomotta.model.Tarefa;
import com.romulomotta.service.TarefasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/")
public class TarefasController {

    @Autowired
    private TarefasService tarefasService;

    @GetMapping
    public String hello() {
        return "Bem vindo ao MS EasyTask";
    }

    @GetMapping("/tarefas/{id}")
    public Tarefa findById(@PathVariable String id){
        return tarefasService.findById(id);
    }

    @GetMapping("/tarefas/descricao/{descricao}")
    public List<Tarefa> findByDescricao(@PathVariable String descricao){
        return tarefasService.findByDescricao(descricao);
    }

    @GetMapping("/tarefas")
    public List<Tarefa> getAll() {
        return tarefasService.findAll();
    }

    @PostMapping("/tarefas")
    public Tarefa create(@RequestBody Tarefa tarefa) {
        return tarefasService.save(tarefa);
    }

    @PutMapping("/tarefas/{id}")
    public void update(@PathVariable String id, @RequestBody Tarefa tarefa){
        tarefasService.update(id, tarefa);
    }

    @DeleteMapping("/tarefas/{id}")
    public void delete(@PathVariable String id){
        tarefasService.deleteById(id);
    }
}
