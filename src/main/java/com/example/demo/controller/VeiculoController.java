package com.example.demo.controller;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    // GET /veiculos
    @GetMapping
    public ResponseEntity<List<RecoveryVeiculoDto>> getVeiculos() {
        return new ResponseEntity<>(veiculoService.getVeiculos(), HttpStatus.OK);
    }

    // GET /veiculos?modelo=Gol
    @GetMapping(params = "modelo")
    public ResponseEntity<List<RecoveryVeiculoDto>> getVeiculosByModelo(@RequestParam String modelo) {
        return new ResponseEntity<>(veiculoService.getVeiculosByModelo(modelo), HttpStatus.OK);
    }

    // GET /veiculos/placa/{placa}
    @GetMapping("/placa/{placa}")
    public ResponseEntity<RecoveryVeiculoDto> getVeiculoByPlaca(@PathVariable String placa) {
        return new ResponseEntity<>(veiculoService.getVeiculoByPlaca(placa), HttpStatus.OK);
    }

    // GET /veiculos/{veiculoId}
    @GetMapping("/{veiculoId}")
    public ResponseEntity<RecoveryVeiculoDto> getVeiculoById(@PathVariable Long veiculoId) {
        return new ResponseEntity<>(veiculoService.getVeiculoById(veiculoId), HttpStatus.OK);
    }
}
