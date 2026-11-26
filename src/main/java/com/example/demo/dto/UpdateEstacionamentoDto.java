package com.example.demo.dto;

public record UpdateEstacionamentoDto(

        String nome,

        EnderecoDto endereco,

        String tipo,

        Boolean coberto,

        Integer andares,

        Double areaTotal,

        Integer capacidade

) {
}
