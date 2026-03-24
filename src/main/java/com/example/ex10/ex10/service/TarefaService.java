package com.example.ex10.ex10.service;

import com.example.ex10.ex10.entity.Tarefa;
import com.example.ex10.ex10.repository.TarefaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    //injetar
    private final TarefaRepository tarefaRepository;

    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    //listar
    public List<Tarefa> findAll(){
        return tarefaRepository.findAll();
    }

    //criar
    public Tarefa save(Tarefa tarefa){
        return tarefaRepository.save(tarefa);
    }

    //deletar
    public void delete(Long id){
        tarefaRepository.deleteById(id);
    }

    //buscar por id
    public Optional<Tarefa> buscar(Long id){
        return tarefaRepository.findById(id);
    }

    //atualizar
    public Tarefa update(Long id, Tarefa tarefa){
        tarefa.setId(id);
        return tarefaRepository.save(tarefa);
    }

}
