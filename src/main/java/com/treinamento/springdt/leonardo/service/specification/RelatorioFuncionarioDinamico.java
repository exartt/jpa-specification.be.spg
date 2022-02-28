package com.treinamento.springdt.leonardo.service.specification;

import com.treinamento.springdt.leonardo.entidades.Funcionario;
import com.treinamento.springdt.leonardo.repository.FuncionarioRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;


@Service
public class RelatorioFuncionarioDinamico {


    private final FuncionarioRepository funcionarioRepository;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public RelatorioFuncionarioDinamico(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void inicial(Scanner scanner){
        System.out.println("Digite o nome");
        String nome = scanner.next();
        System.out.println("Digite o CPF");
        String cpf = scanner.next();
        System.out.println("Digite o Salario");
        Double salario = scanner.nextDouble();
        System.out.println("Digite a data de contratação");
        String dataScan = scanner.next();

        LocalDate data;
        if(dataScan.equalsIgnoreCase("NULL")) {
            data = null;
        } else {
            data = LocalDate.parse(dataScan, formatter);
        }

        if(nome.equalsIgnoreCase("NULL")) {
            nome = null;
        }

        if(cpf.equalsIgnoreCase("NULL")) {
            cpf = null;
        }

        if(dataScan.equalsIgnoreCase("NULL")) {
            data = null;
        }
        List<Funcionario> funcionarios = funcionarioRepository
                .findAll(Specification
                        .where(SpecificationFuncionario.nome(nome))
                        .or(SpecificationFuncionario.cpf((cpf)))
                        .or(SpecificationFuncionario.salario(salario))
                        .or(SpecificationFuncionario.data(data)));
    }
}
