package com.treinamento.springdt.leonardo.repository;

import com.treinamento.springdt.leonardo.entidades.Cargo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepository extends CrudRepository<Cargo, Integer> {

}
