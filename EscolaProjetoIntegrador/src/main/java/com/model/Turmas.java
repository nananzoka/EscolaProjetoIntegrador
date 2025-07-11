package com.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "turma")
@Getter
@Setter
public class Turmas {
    
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id; 

    private String nome;
    private String serieEscolar;
    private String turno; // Matutino, Vespertino, Noturno
    private String ano;
    private String sala;  
    private String horarioaula;
    private String status; // Ativo, Inativo, Concluida
    private String disciplinas;

    public long getId() {
        return this.id != null ? this.id : 0L;
    }

}
