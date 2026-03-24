package com.example.ex10.ex10.controller;

import com.example.ex10.ex10.entity.Tarefa;
import com.example.ex10.ex10.repository.TarefaRepository;
import com.example.ex10.ex10.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    //injecao de dependencia
    @Autowired
    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @GetMapping
    public ResponseEntity<List<Tarefa>> findAll(){
        List<Tarefa> resquest = tarefaService.findAll();
        return ResponseEntity.ok().body(resquest);
    }

    @PostMapping
    public ResponseEntity<Tarefa> save(@RequestBody Tarefa tarefa){
        Tarefa save = tarefaService.save(tarefa);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(tarefa.getId()).toUri();
        return ResponseEntity.created(uri).body(save);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        tarefaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> findById(@PathVariable Long id){
        return tarefaService.buscar(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> update(@PathVariable Long id, @RequestBody Tarefa tarefa){
        return ResponseEntity.ok(tarefaService.update(id, tarefa));
    }

}
