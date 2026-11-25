package com.example.demo.controller;

import com.example.demo.dto_old.*;
import com.example.demo.services.ProductService;
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

    // GET /estacionamentos
    @GetMapping
    public ResponseEntity<List<RecoveryEstacionamentoDto>> getEstacionamentos() {
        return new ResponseEntity<>(estacionamentoService.getEstacionamentos(), HttpStatus.OK);
    }

    // GET /estacionamentos?tipo=PUBLICO
    @GetMapping(params = "tipo")
    public ResponseEntity<List<RecoveryEstacionamentoDto>> getEstacionamentosByTipo(@RequestParam String tipo) {
        return new ResponseEntity<>(estacionamentoService.getEstacionamentosByTipo(tipo), HttpStatus.OK);
    }

    // GET /estacionamentos/lotados
    @GetMapping("/lotados")
    public ResponseEntity<List<RecoveryEstacionamentoDto>> getEstacionamentosLotados() {
        return new ResponseEntity<>(estacionamentoService.getEstacionamentosLotados(), HttpStatus.OK);
    }

    // GET /estacionamentos/{estacionamentoId}
    @GetMapping("/{estacionamentoId}")
    public ResponseEntity<RecoveryEstacionamentoDto> getEstacionamentoById(@PathVariable Long estacionamentoId) {
        return new ResponseEntity<>(estacionamentoService.getEstacionamentoById(estacionamentoId), HttpStatus.OK);
    }

    // POST, PATCH e DELETE seguem o mesmo formato do ProductController
}

    @DeleteMapping("/{productId}/variation/{productVariationId}")
    public ResponseEntity<Void> deleteProductVariationById(@PathVariable Long productId, @PathVariable Long productVariationId) {
        productService.deleteProductVariationById(productId, productVariationId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
