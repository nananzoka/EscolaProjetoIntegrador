package com.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "notas")
@Getter
@Setter
public class Notas {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id; // ID da Nota

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno; // Referência ao aluno

    @ManyToOne
    @JoinColumn(name = "disciplina_id")
    private Disciplinas disciplina; // Referência à disciplina

    @ManyToOne
    @JoinColumn(name = "turma_id")
    private Turmas turma; // Referência à turma

    private String tipoAvaliacao; // Prova, Trabalho, Atividade, Recuperação

    private Double nota; // Valor da nota (0 a 10)

    private LocalDate dataAvaliacao; // Data em que foi aplicada

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professores professor; // Referência ao professor que aplicou
}