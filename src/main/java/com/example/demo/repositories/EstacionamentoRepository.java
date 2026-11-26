package com.example.demo.repositories;

import com.example.demo.models.Estacionamento;
import com.example.demo.models.TipoEstacionamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstacionamentoRepository extends JpaRepository<Estacionamento, Long> {

    List<Estacionamento> findByTipo(TipoEstacionamento tipo);

    @Query("select e from Estacionamento e where e.capacidade is not null and size(e.veiculos) >= e.capacidade")
    List<Estacionamento> findLotados();
}
