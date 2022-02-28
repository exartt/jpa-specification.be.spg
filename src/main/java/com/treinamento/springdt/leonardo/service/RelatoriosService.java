package com.treinamento.springdt.leonardo.service;


import com.sun.org.apache.xpath.internal.operations.Bool;
import com.treinamento.springdt.leonardo.entidades.Funcionario;
import com.treinamento.springdt.leonardo.entidades.FuncionarioProjecao;
import com.treinamento.springdt.leonardo.repository.FuncionarioRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatoriosService {

    private Boolean system = true;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final FuncionarioRepository funcionarioRepository;
    public RelatoriosService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void inicial(Scanner scanner) {
        while (system){
            System.out.println("Qual ação deseja executar?");
            System.out.println("0 - Sair");
            System.out.println("1 - Busca funcionario nome");
            System.out.println("2 - Busca funcionario nome, salario e data");
            System.out.println("3 - Busca funcionario data contratação");
            System.out.println("4 - Busca salario do funcionario");

            int action = scanner.nextInt();
            switch (action) {
                case 1:
                    buscaFuncionarioNome(scanner);
                    break;
                case 2:
                    buscaFuncionarioNomeSalarioMaiorData(scanner);
                    break;
                case 3:
                    buscaFuncionarioDataContratacao(scanner);
                    break;
                case 4:
                    pesquisafuncionarioSalario();
                default:
                    system = false;
                    break;
            }
        }

    }
    /*
    Basicamente o que faremos nas funções a seguir serão buscas.
    Essas buscas estão vinculadas a querys nos repositorios
    (nesse caso do funcionario)
    */

    private void buscaFuncionarioNome(Scanner scanner) {
        System.out.println("Digite o nome a procurar ");
        String nome = scanner.next();
        List<Funcionario> list = funcionarioRepository.findByNome(nome);
        list.forEach(System.out::println);
    }

    private void buscaFuncionarioNomeSalarioMaiorData(Scanner scanner){
        System.out.println("Qual o nome para pesquisar");
        String nome = scanner.next();
        System.out.println("Qual a data de contratação");
        String data = scanner.next();
        LocalDate localDate = LocalDate.parse(data, formatter);
        System.out.println("Qual o valor do salario a ser procurado");
        Double salario = scanner.nextDouble();

        List<Funcionario> list = funcionarioRepository.findNomeSalarioMaiorDataContratacao(nome,salario,localDate);
    }

    private void buscaFuncionarioDataContratacao(Scanner scanner){
        System.out.println("Qual a data de contratação que deseja pesquisar? ");
        String data = scanner.next();
        LocalDate localDate = LocalDate.parse(data, formatter);
        List<Funcionario> list = funcionarioRepository.findDataContratacaoMaior(localDate);
        list.forEach(System.out::println);
    }


    //Uma query mais especifica, ela busca exatamente esses elementos sem ter que puxar tudo e filtrar depois.
    private void pesquisafuncionarioSalario () {
        List<FuncionarioProjecao> list = funcionarioRepository.findFuncionarioSalario();
        list.forEach(f -> System.out.println("Funcionario: id: " + f.getNome()
        + " | nome: " + f.getNome() + " | salario: " + f.getSalario()));
    }


}
