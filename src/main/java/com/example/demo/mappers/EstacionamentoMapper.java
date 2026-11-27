package com.example.demo.mappers;

import com.example.demo.dto.EnderecoDto;
import com.example.demo.dto.RecoveryEstacionamentoDto;
import com.example.demo.models.Endereco;
import com.example.demo.models.Estacionamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EstacionamentoMapper {

    @Autowired
    private VeiculoMapper veiculoMapper;

    public RecoveryEstacionamentoDto toRecoveryEstacionamentoDto(Estacionamento estacionamento) {
        return new RecoveryEstacionamentoDto(
                estacionamento.getId(),
                estacionamento.getNome(),
                toEnderecoDto(estacionamento.getEndereco()),
                estacionamento.getTipo() != null ? estacionamento.getTipo().name() : null,
                estacionamento.getCoberto(),
                estacionamento.getAndares(),
                estacionamento.getAreaTotal(),
                estacionamento.getCapacidade(),
                isLotado(estacionamento),
                estacionamento.getVeiculos().stream()
                        .map(veiculoMapper::toRecoveryVeiculoDto)
                        .toList()
        );
    }

    public boolean isLotado(Estacionamento estacionamento) {
        return estacionamento.getCapacidade() != null
                && estacionamento.getVeiculos().size() >= estacionamento.getCapacidade();
    }

    public EnderecoDto toEnderecoDto(Endereco endereco) {
        if (endereco == null) {
            return null;
        }
        return new EnderecoDto(
                endereco.getPais(),
                endereco.getEstado(),
                endereco.getCidade(),
                endereco.getBairro(),
                endereco.getRua()
        );
    }

    public Endereco toEndereco(EnderecoDto enderecoDto) {
        if (enderecoDto == null) {
            return null;
        }
        return Endereco.builder()
                .pais(enderecoDto.pais())
                .estado(enderecoDto.estado())
                .cidade(enderecoDto.cidade())
                .bairro(enderecoDto.bairro())
                .rua(enderecoDto.rua())
                .build();
    }
}
