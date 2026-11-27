package com.example.demo.mappers;

import com.example.demo.dto.RecoveryVeiculoDto;
import com.example.demo.models.Veiculo;
import org.springframework.stereotype.Component;

@Component
public class VeiculoMapper {

    public RecoveryVeiculoDto toRecoveryVeiculoDto(Veiculo veiculo) {
        return new RecoveryVeiculoDto(
                veiculo.getId(),
                veiculo.getModelo(),
                veiculo.getPlaca(),
                veiculo.getTipo() != null ? veiculo.getTipo().name() : null,
                veiculo.getEstacionamento() != null ? veiculo.getEstacionamento().getId() : null
        );
    }
}
