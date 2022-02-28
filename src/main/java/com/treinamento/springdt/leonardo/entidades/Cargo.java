package com.treinamento.springdt.leonardo.entidades;

import lombok.Getter;
import lombok.Setter;
import sun.awt.geom.AreaOp;

import javax.persistence.*;

@Entity
@Table( name="cargos")

public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String descricao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
