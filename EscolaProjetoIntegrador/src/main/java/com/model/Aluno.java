package com.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "aluno", uniqueConstraints = { @UniqueConstraint(columnNames = { "email" }) })
@Getter
@Setter
public class Aluno {
    
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cidade;
    private String estado;
    private String telefone;
    private Long   turma;
    private String status;     // Matriculado, Transferido, Concluído, Cancelado     
   
    // LOGIN
    private String email;
    private String senha;

    public long getId() {
        return this.id != null ? this.id : 0L;
    }
}