package com.example.demo.dto;

public record UpdateVeiculoDto(

        String modelo,

        String placa,

        String tipo,

        Long estacionamentoId

) {
}
