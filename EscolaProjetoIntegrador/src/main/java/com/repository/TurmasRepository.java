package com.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.model.Turmas;

@Repository                                            
public interface TurmasRepository extends JpaRepository< Turmas, Long> {

}

// teste

