package com.example.demo.services;

import com.example.demo.models.Estacionamento;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EstacionamentoService {

    public List<Estacionamento> getEstacionamentos() {
        return List.of();
    }

    public List<Estacionamento> getEstacionamentosByTipo(String tipo) {
        return List.of();
    }

    public List<Estacionamento> getEstacionamentosLotados() {
        return List.of();
    }

    public Estacionamento getEstacionamentoById(Long estacionamentoId) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "Busca por id ainda não implementada.");
    }
}
