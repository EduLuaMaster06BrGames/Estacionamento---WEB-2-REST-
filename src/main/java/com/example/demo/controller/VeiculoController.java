package com.example.demo.controller;

import com.example.demo.models.Veiculo;
import com.example.demo.services.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @GetMapping
    public ResponseEntity<List<Veiculo>> getVeiculos() {
        return new ResponseEntity<>(veiculoService.getVeiculos(), HttpStatus.OK);
    }

    @GetMapping(params = "modelo")
    public ResponseEntity<List<Veiculo>> getVeiculosByModelo(@RequestParam String modelo) {
        return new ResponseEntity<>(veiculoService.getVeiculosByModelo(modelo), HttpStatus.OK);
    }

    @GetMapping("/placa/{placa}")
    public ResponseEntity<Veiculo> getVeiculoByPlaca(@PathVariable String placa) {
        return new ResponseEntity<>(veiculoService.getVeiculoByPlaca(placa), HttpStatus.OK);
    }

    @GetMapping("/{veiculoId}")
    public ResponseEntity<Veiculo> getVeiculoById(@PathVariable Long veiculoId) {
        return new ResponseEntity<>(veiculoService.getVeiculoById(veiculoId), HttpStatus.OK);
    }
}
