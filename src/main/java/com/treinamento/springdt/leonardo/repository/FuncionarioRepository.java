package com.treinamento.springdt.leonardo.repository;

import com.treinamento.springdt.leonardo.entidades.Funcionario;
import com.treinamento.springdt.leonardo.entidades.FuncionarioProjecao;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDate;
import java.util.List;

public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario,Integer>,
        JpaSpecificationExecutor<Funcionario> {
    List<Funcionario> findByNome (String nome);

    /*
    Para simplificar e reduzir o nome das querys, estamos fazendo anotação da query
    pois assim acessamos da mesma forma e simplificamos o nome (que pode ficar muito extenso)
    */
    @Query("SELECT f FROM  Funcionario f WHERE f.nome = :nome AND" +
            " f.salario >= :salario AND f.data = :data")
    List<Funcionario> findNomeSalarioMaiorDataContratacao (String nome,
                                                           Double salario,
                                                           LocalDate data);
    //Para falar pro spring data executar como native query temos que atribuir
    //em forma de valor a query.
    @Query(value = "SELECT * FROM funcionarios f WHERE f.data >= :data",
            nativeQuery = true)
    List<Funcionario> findDataContratacaoMaior(LocalDate data);

    @Query(value = "SELECT f.id, f.nome, f.salario FROM funcionarios f",
            nativeQuery = true)
    List<FuncionarioProjecao> findFuncionarioSalario ();
}
