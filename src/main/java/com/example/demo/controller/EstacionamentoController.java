package com.example.demo.controller;

import com.example.demo.dto.CreateEstacionamentoDto;
import com.example.demo.dto.RecoveryEstacionamentoDto;
import com.example.demo.dto.UpdateEstacionamentoDto;
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

    @PostMapping
    public ResponseEntity<RecoveryEstacionamentoDto> createEstacionamento(@RequestBody CreateEstacionamentoDto dto) {
        return new ResponseEntity<>(estacionamentoService.createEstacionamento(dto), HttpStatus.CREATED);
    }

    @PatchMapping("/{estacionamentoId}")
    public ResponseEntity<RecoveryEstacionamentoDto> updateEstacionamento(@PathVariable Long estacionamentoId,
                                                                          @RequestBody UpdateEstacionamentoDto dto) {
        return new ResponseEntity<>(estacionamentoService.updateEstacionamento(estacionamentoId, dto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<RecoveryEstacionamentoDto>> getEstacionamentos() {
        return new ResponseEntity<>(estacionamentoService.getEstacionamentos(), HttpStatus.OK);
    }

    @GetMapping(params = "tipo")
    public ResponseEntity<List<RecoveryEstacionamentoDto>> getEstacionamentosByTipo(@RequestParam String tipo) {
        return new ResponseEntity<>(estacionamentoService.getEstacionamentosByTipo(tipo), HttpStatus.OK);
    }

    @GetMapping("/lotados")
    public ResponseEntity<List<RecoveryEstacionamentoDto>> getEstacionamentosLotados() {
        return new ResponseEntity<>(estacionamentoService.getEstacionamentosLotados(), HttpStatus.OK);
    }

    @GetMapping("/{estacionamentoId}")
    public ResponseEntity<RecoveryEstacionamentoDto> getEstacionamentoById(@PathVariable Long estacionamentoId) {
        return new ResponseEntity<>(estacionamentoService.getEstacionamentoById(estacionamentoId), HttpStatus.OK);
    }

    @DeleteMapping("/{estacionamentoId}")
    public ResponseEntity<Void> deleteEstacionamentoById(@PathVariable Long estacionamentoId) {
        estacionamentoService.deleteEstacionamentoById(estacionamentoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
