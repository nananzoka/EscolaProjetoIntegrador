package com.controller;

import java.util.List;
import java.util.Optional;

import com.model.Notas;
import com.repository.NotasRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notas")
public class ControllerNotas {

    @Autowired
    private NotasRepository notasRepository;

    @GetMapping
    public List<Notas> listarNotas() {
        return notasRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Notas> buscarNotaPorId(@PathVariable Long id) {
        return notasRepository.findById(id);
    }

    @PostMapping
    public Notas criarNota(@RequestBody Notas nota) {
        return notasRepository.save(nota);
    }

    @PutMapping("/{id}")
    public Notas atualizarNota(@PathVariable Long id, @RequestBody Notas notaAtualizada) {
        return notasRepository.findById(id).map(nota -> {
            nota.setAluno(notaAtualizada.getAluno());
            nota.setDisciplina(notaAtualizada.getDisciplina());
            nota.setTurma(notaAtualizada.getTurma());
            nota.setTipoAvaliacao(notaAtualizada.getTipoAvaliacao());
            nota.setNota(notaAtualizada.getNota());
            nota.setDataAvaliacao(notaAtualizada.getDataAvaliacao());
            nota.setProfessor(notaAtualizada.getProfessor());
            return notasRepository.save(nota);
        }).orElseGet(() -> {
            notaAtualizada.setId(id);
            return notasRepository.save(notaAtualizada);
        });
    }

    @DeleteMapping("/{id}")
    public void deletarNota(@PathVariable Long id) {
        notasRepository.deleteById(id);
    }
}