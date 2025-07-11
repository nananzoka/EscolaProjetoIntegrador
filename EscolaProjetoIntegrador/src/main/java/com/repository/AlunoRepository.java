package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Aluno;

@Repository                                            
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

     /**
     * Finds a Aluno by email and senha.
     *
     * @param email the email of the Aluno
     * @param senha the senha of the Aluno
     * @return an Optional containing the found Aluno, or empty if not found
     */
    Optional<Aluno> findAlunoByEmailAndSenha(String email, String senha);
}

// teste