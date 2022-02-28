package com.treinamento.springdt.leonardo.service.specification;

import com.treinamento.springdt.leonardo.entidades.Funcionario;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class SpecificationFuncionario {
    public static Specification<Funcionario> nome(String nome) {
        return (root,criteriaQuery, criteriaBuilder) ->
                criteriaBuilder
                        .like(root.get("nome"),"%" + nome + "%");
    }

    public static Specification<Funcionario> cpf(String cpf) {
        return (root,criteriaQuery, criteriaBuilder) ->
                criteriaBuilder
                        .like(root.get("cpf"), cpf);
    }

    public static Specification<Funcionario> salario(Double salario) {
        return (root,criteriaQuery, criteriaBuilder) ->
                criteriaBuilder
                        .greaterThan(root.get("salario"),salario);
    }

    public static Specification<Funcionario> data(LocalDate date) {
        return (root,criteriaQuery, criteriaBuilder) ->
                criteriaBuilder
                        .greaterThan(root.get("date"),date);
    }
}
