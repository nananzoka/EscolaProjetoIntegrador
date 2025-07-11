package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
import com.model.Aluno;
import com.model.Login;
import com.repository.AlunoRepository;

@RestController
@RequestMapping("/api/v1/alunos")
public class ControllerAluno {
    
    @Autowired 
    private AlunoRepository repository;


	@GetMapping()
	@ResponseStatus(HttpStatus.OK)
	public List<Aluno> getAll() {
		return this.repository.findAll();
	}

	// Inserir
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)	
	public Aluno createCadastro(@RequestBody Aluno model){			
		return this.repository.save(model);
	}

    // Listar
	@GetMapping("/{usuario_id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Aluno> getCadastroById(@PathVariable(value = "usuario_id") Long cadastroId)
		throws ResourceNotFoundException {
		
			Aluno cadastro = repository.findById(cadastroId)
			.orElseThrow(() -> new ResourceNotFoundException(
					"Usuario não encontrado para o ID: " + cadastroId));

		return ResponseEntity.ok().body(cadastro);
	}

	// Atualizar
    @PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Aluno updateCadastro(@PathVariable Long id, @RequestBody Aluno model)
		throws ResourceNotFoundException {
		// Verifica se o cadastro existe
		Aluno cadastro = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException
						("Cadastro não encontrado para o ID : " + id));
        
		if (model.getNome() != null) {
			cadastro.setNome(model.getNome());
		}

		if (model.getEmail() != null) {
			cadastro.setEmail(model.getEmail());
		}

		if (model.getCidade() != null) {
			cadastro.setCidade(model.getCidade());
		}

		if (model.getEstado() != null) {
			cadastro.setEstado(model.getEstado());
		}

		if (model.getTelefone() != null) {
			cadastro.setTelefone(model.getTelefone());
		}

		if (model.getTurma() != null) {
			cadastro.setTurma(model.getTurma());
		}

		if (model.getStatus() != null) {
			cadastro.setStatus(model.getStatus());
		}

		if (model.getSenha() != null) {
			cadastro.setSenha(model.getSenha());
		}

		return this.repository.save(cadastro);
	}

	// Deletar
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void excluir(@PathVariable Long id) {
        this.repository.deleteById(id);
    }

	// Login
	@PostMapping("/login")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Login> login(@Validated @RequestBody Login cadastro) throws ResourceNotFoundException {
		String email = cadastro.getEmail();
		String senha = cadastro.getSenha();

		Aluno usuario = this.repository.findAlunoByEmailAndSenha(email, senha)
				.orElseThrow(() -> new ResourceNotFoundException("Usuario ou senha inválido!"));

		cadastro.setAluno(usuario);
		cadastro.setData("DATA ATUAL");

		return ResponseEntity.ok().body(cadastro);
	}
}