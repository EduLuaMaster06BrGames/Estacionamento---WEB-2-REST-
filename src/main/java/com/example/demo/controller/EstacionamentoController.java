package com.example.demo.controller;

import com.example.demo.models.Estacionamento;
import com.example.demo.services.EstacionamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estacionamentos")
public class EstacionamentoController {

    @Autowired
    private EstacionamentoService estacionamentoService;

    @GetMapping
    public ResponseEntity<List<Estacionamento>> getEstacionamentos() {
        return new ResponseEntity<>(estacionamentoService.getEstacionamentos(), HttpStatus.OK);
    }

    @GetMapping(params = "tipo")
    public ResponseEntity<List<Estacionamento>> getEstacionamentosByTipo(@RequestParam String tipo) {
        return new ResponseEntity<>(estacionamentoService.getEstacionamentosByTipo(tipo), HttpStatus.OK);
    }

    @GetMapping("/lotados")
    public ResponseEntity<List<Estacionamento>> getEstacionamentosLotados() {
        return new ResponseEntity<>(estacionamentoService.getEstacionamentosLotados(), HttpStatus.OK);
    }

    @GetMapping("/{estacionamentoId}")
    public ResponseEntity<Estacionamento> getEstacionamentoById(@PathVariable Long estacionamentoId) {
        return new ResponseEntity<>(estacionamentoService.getEstacionamentoById(estacionamentoId), HttpStatus.OK);
    }
}
