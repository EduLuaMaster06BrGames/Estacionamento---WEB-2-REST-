package com.example.demo.dto;

public record CreateVeiculoDto(

        String modelo,

        String placa,

        String tipo,

        Long estacionamentoId

) {
}
