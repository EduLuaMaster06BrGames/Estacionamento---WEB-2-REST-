package com.example.demo.dto;

import java.util.List;

public record RecoveryEstacionamentoDto(

        Long id,

        String nome,

        EnderecoDto endereco,

        String tipo,

        Boolean coberto,

        Integer andares,

        Double areaTotal,

        Integer capacidade,

        Boolean lotado,

        List<RecoveryVeiculoDto> veiculos

) {
}
