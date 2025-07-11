package com.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.model.Professores;

@Repository                                            
public interface ProfessoresRepository extends JpaRepository< Professores, Long> {


    /**
     * Finds a Professor by email and senha.
     *
     * @param email the email of the Professor
     * @param senha the senha of the Professor
     * @return an Optional containing the found Professor, or empty if not found
     */
    Optional<Professores> findProfessoresByEmailAndSenha(String email, String senha);
}

// teste

