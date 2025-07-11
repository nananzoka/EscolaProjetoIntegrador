package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.exception.ResourceNotFoundException;
import com.model.Turmas;
import com.repository.TurmasRepository;

@RestController
@RequestMapping("/api/v1/turmas")
public class ControllerTurmas {

    @Autowired
    private TurmasRepository repository;

    // Listar todos
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Turmas> getAll() {
        return this.repository.findAll();
    }

    // Inserir
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Turmas createCadastro(@RequestBody Turmas model) {
        return this.repository.save(model);
    }

    // Listar
    @GetMapping("/{usuario_id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Turmas> getCadastroById(@PathVariable(value = "usuario_id") Long cadastroId)
            throws ResourceNotFoundException {
        // select * from usuario where id = cadastroId
        Turmas cadastro = repository.findById(cadastroId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Usuario não encontrado para o ID: " + cadastroId));

        return ResponseEntity.ok().body(cadastro);
    }

    // Atualizar
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Turmas updateCadastro(@PathVariable Long id, @RequestBody Turmas model)
            throws ResourceNotFoundException {

        Turmas cadastro = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cadastro não encontrado para o ID : " + id));

        if (model.getNome() != null) {
            cadastro.setNome(model.getNome());
        }
        if (model.getTurno() != null) {
            cadastro.setTurno(model.getTurno());
        }
        if (model.getAno() != null) {
            cadastro.setAno(model.getAno());
        }
        if (model.getSala() != null) {
            cadastro.setSala(model.getSala());
        }
        if (model.getHorarioaula() != null) {
            cadastro.setHorarioaula(model.getHorarioaula());
        }
        if (model.getStatus() != null) {
            cadastro.setStatus(model.getStatus());
        }
        if (model.getDisciplinas() != null) {
            cadastro.setDisciplinas(model.getDisciplinas());
        }

        return repository.save(cadastro); // << Adiciona o return final
    }

    // Deletar
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void excluir(@PathVariable Long id) {
        this.repository.deleteById(id);
    }

}
