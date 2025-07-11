package com.controller;

import com.exception.ResourceNotFoundException;
import com.model.Disciplinas;
import com.repository.DisciplinasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/disciplinas")
public class ControllerDisciplinas {

    @Autowired
    private DisciplinasRepository disciplinaRepository;

    @PostMapping()
	@ResponseStatus(HttpStatus.CREATED)	
	public Disciplinas inserirDisciplina(@RequestBody Disciplinas model){			
		return this.disciplinaRepository.save(model);
	}
    
    @GetMapping
    public List<Disciplinas> listarDisciplinas() {
        return disciplinaRepository.findAll();
    }

	// Atualizar
    @PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Disciplinas updateCadastro(@PathVariable Long id, @RequestBody Disciplinas model)
		throws ResourceNotFoundException {
		// Verifica se o cadastro existe
		Disciplinas cadastro = disciplinaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException
						("Cadastro não encontrado para o ID : " + id));
        
		if (model.getNome() != null) {
			cadastro.setNome(model.getNome());
		}

        if (model.getSerie() != null) {
			cadastro.setSerie(model.getSerie());
		}

        if (model.getProfessor() != null) {
			cadastro.setProfessor(model.getProfessor());
		}

		if (model.getStatus() != null) {
			cadastro.setStatus(model.getStatus());
		}		

		return this.disciplinaRepository.save(cadastro);
	}
}
