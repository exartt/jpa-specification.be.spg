package com.treinamento.springdt.leonardo;

import com.treinamento.springdt.leonardo.service.CrudCargoService;
import com.treinamento.springdt.leonardo.service.CrudFuncionarioService;
import com.treinamento.springdt.leonardo.service.CrudUnidadeTrabalhoService;
import com.treinamento.springdt.leonardo.service.RelatoriosService;
import com.treinamento.springdt.leonardo.service.specification.RelatorioFuncionarioDinamico;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SpringdtLeonardoApplication implements CommandLineRunner {

    private boolean system = true;

    private final CrudCargoService cargoService;

    private final RelatoriosService relatoriosService;

    private final CrudFuncionarioService crudFuncionarioService;

    private final CrudUnidadeTrabalhoService crudUnidadeTrabalhoService;

    private final RelatorioFuncionarioDinamico relatorioFuncionarioDinamico;


    public SpringdtLeonardoApplication(CrudCargoService cargoService,
                                       RelatoriosService relatoriosService,
                                       CrudFuncionarioService crudFuncionarioService,
                                       CrudUnidadeTrabalhoService crudUnidadeTrabalhoService,
                                       RelatorioFuncionarioDinamico relatorioFuncionarioDinamico){
        this.cargoService = cargoService;
        this.crudFuncionarioService = crudFuncionarioService;
        this.crudUnidadeTrabalhoService = crudUnidadeTrabalhoService;
        this.relatoriosService = relatoriosService;
        this.relatorioFuncionarioDinamico = relatorioFuncionarioDinamico;
    }

    public static void main(String[] args) {

        SpringApplication.run(SpringdtLeonardoApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (system) {
            System.out.println("Qual ação executar? ");
            System.out.println("0 - Sair ");
            System.out.println("1 - Cargo");
            System.out.println("2 - funcionario");
            System.out.println("3 - Unidade Trabalho");
            System.out.println("4 - Relatorios");
            System.out.println("5 - RelatoriosFuncionarioDinamico");

            int action = scanner.nextInt();
            switch (action){
                case 1:
                    cargoService.inicial(scanner);
                    break;
                case 2:
                    crudFuncionarioService.inicial(scanner);
                    break;
                case 3:
                    crudUnidadeTrabalhoService.inicial(scanner);
                    break;
                case 4:
                    relatoriosService.inicial(scanner);
                    break;
                case 5:
                    relatorioFuncionarioDinamico.inicial(scanner);
                    break;
                default:
                    break;
            }
        }
    }
}
