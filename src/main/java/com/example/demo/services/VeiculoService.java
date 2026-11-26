package com.example.demo.services;

import com.example.demo.models.Veiculo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class VeiculoService {

    public List<Veiculo> getVeiculos() {
        return List.of();
    }

    public List<Veiculo> getVeiculosByModelo(String modelo) {
        return List.of();
    }

    public Veiculo getVeiculoByPlaca(String placa) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "Busca por placa ainda não implementada.");
    }

    public Veiculo getVeiculoById(Long veiculoId) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "Busca por id ainda não implementada.");
    }
}
