package com.treinamento.springdt.leonardo.service;

import com.treinamento.springdt.leonardo.entidades.Cargo;
import com.treinamento.springdt.leonardo.entidades.Funcionario;
import com.treinamento.springdt.leonardo.entidades.UnidadeTrabalho;
import com.treinamento.springdt.leonardo.repository.CargoRepository;
import com.treinamento.springdt.leonardo.repository.UnidadeTrabalhoRepository;
import com.treinamento.springdt.leonardo.repository.FuncionarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudFuncionarioService {

    private Boolean system = true;
    private final FuncionarioRepository funcionarioRepository;
    private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;
    private final CargoRepository cargoRepository;

    public CrudFuncionarioService(FuncionarioRepository funcionarioRepository,
                                  UnidadeTrabalhoRepository unidadeTrabalhoRepository,
                                  CargoRepository cargoRepository){
        this.funcionarioRepository = funcionarioRepository;
        this.cargoRepository = cargoRepository;
        this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
    }

    public void inicial(Scanner scanner) {
        while (system){
            System.out.println("Qual ação deseja executar?");
            System.out.println("0 - Sair");
            System.out.println("1 - Salvar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Visualizar");

            int action = scanner.nextInt();
            switch (action) {
                case 1:
                    salvar(scanner);
                    break;
                case 2:
                    salvar(scanner);
                    break;
                case 3:
                    visualizar(scanner);
                    break;
                case 4:
                    deletar(scanner);
                    break;
                default:
                    system = false;
                    break;
            }
        }

    }
    private List<UnidadeTrabalho> unidade(Scanner scanner){
        boolean isTrue = true;
        List<UnidadeTrabalho> unidades = new ArrayList<>();

        while (isTrue) {
            System.out.println("Digite o Id da unidade (Para sair digite 0)");
            Integer unidadeId = scanner.nextInt();

            if(unidadeId != 0) {
                Optional<UnidadeTrabalho> unidade = unidadeTrabalhoRepository.findById(unidadeId);
                unidades.add(unidade.get());
            } else {
                isTrue = false;
            }
        }
        return unidades;
    }
    private void salvar (Scanner scanner) {
        System.out.println("Nome do funcionario ");
        String nome = scanner.next();
        System.out.println("Cpf do funcionario ");
        String cpf = scanner.next();
        System.out.println("Cargo do funcionario ");
        Integer cargoId = scanner.nextInt();
        System.out.println("Salario do funcionario ");
        Double salario = scanner.nextDouble();
        System.out.println("Data de Contratação do funcionario ");
        String dataContratacao = scanner.next();
        List<UnidadeTrabalho> unidades = unidade(scanner);
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        Optional<Cargo> cargo = cargoRepository.findById(cargoId);
        funcionario.setCargo(cargo.get());
        funcionario.setSalario(salario);
        funcionario.setUnidadeTrabalhos(unidades);
        funcionarioRepository.save(funcionario);
        System.out.println("Salvo!");
    }

    private void atualizar(Scanner scanner){
        System.out.println("Id");
        int id = scanner.nextInt();
        System.out.println("Nome do funcionario ");
        String nome = scanner.next();
        System.out.println("Cpf do funcionario ");
        String cpf = scanner.next();
        System.out.println("Cargo do funcionario ");
        Integer cargoId = scanner.nextInt();
        System.out.println("Salario do funcionario ");
        Double salario = scanner.nextDouble();
        System.out.println("Data de Contratação do funcionario ");
        String dataContratacao = scanner.next();

        Funcionario funcionario = new Funcionario();
        funcionario.setId(id);
        funcionario.setCpf(cpf);
        funcionario.setNome(nome);
        funcionario.setSalario(salario);
        Optional<Cargo> cargo = cargoRepository.findById(cargoId);
        funcionario.setCargo(cargo.get());

        funcionarioRepository.save(funcionario);
        System.out.println("Salvo");
    }

    private void visualizar (Scanner scanner) {
        System.out.println("Qual pagina você deseja visualizar? ");
        Integer page = scanner.nextInt();

        Pageable pageable = PageRequest.of(page,5,
                                            Sort.by(Sort.Direction.ASC,
                                                    "nome"));
        Page<Funcionario> funcionarios = funcionarioRepository.findAll(pageable);

        System.out.println(funcionarios);
        System.out.println("Pagina atual " + funcionarios.getNumber());
        System.out.println("Total elemento" + funcionarios.getTotalElements());
        funcionarios.forEach(funcionario -> System.out.println(funcionario));
    }

    private void deletar(Scanner scanner){
        System.out.println("Id");
        int id = scanner.nextInt();
        funcionarioRepository.deleteById(id);
        System.out.println("Deletado com sucesso");
    }
}
