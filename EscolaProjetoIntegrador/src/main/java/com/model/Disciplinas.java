package com.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "disciplinas")
@Getter
@Setter

public class Disciplinas {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String serie;
    private String professor;
    private String status;

    public long getId() {
        return this.id != null ? this.id : 0L;
    }
}