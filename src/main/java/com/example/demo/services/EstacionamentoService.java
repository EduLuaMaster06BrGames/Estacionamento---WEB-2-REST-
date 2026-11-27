package com.example.demo.services;

import com.example.demo.dto.CreateEstacionamentoDto;
import com.example.demo.dto.EnderecoDto;
import com.example.demo.dto.RecoveryEstacionamentoDto;
import com.example.demo.dto.UpdateEstacionamentoDto;
import com.example.demo.mappers.EstacionamentoMapper;
import com.example.demo.models.Endereco;
import com.example.demo.models.Estacionamento;
import com.example.demo.models.TipoEstacionamento;
import com.example.demo.repositories.EstacionamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

@Service
public class EstacionamentoService {

    @Autowired
    private EstacionamentoRepository estacionamentoRepository;

    @Autowired
    private EstacionamentoMapper estacionamentoMapper;

    @Transactional
    public RecoveryEstacionamentoDto createEstacionamento(CreateEstacionamentoDto dto) {
        if (dto.nome() == null || dto.nome().isBlank()) {
            throw erro(HttpStatus.BAD_REQUEST, "O nome do estacionamento é obrigatório.");
        }
        if (dto.endereco() == null) {
            throw erro(HttpStatus.BAD_REQUEST, "O endereço do estacionamento é obrigatório.");
        }
        if (dto.capacidade() == null) {
            throw erro(HttpStatus.BAD_REQUEST, "A capacidade do estacionamento é obrigatória.");
        }
        validarNumeros(dto.andares(), dto.areaTotal(), dto.capacidade());

        Estacionamento estacionamento = Estacionamento.builder()
                .nome(dto.nome())
                .endereco(estacionamentoMapper.toEndereco(dto.endereco()))
                .tipo(converterTipo(dto.tipo()))
                .coberto(dto.coberto())
                .andares(dto.andares())
                .areaTotal(dto.areaTotal())
                .capacidade(dto.capacidade())
                .build();

        return estacionamentoMapper.toRecoveryEstacionamentoDto(estacionamentoRepository.save(estacionamento));
    }

    @Transactional
    public RecoveryEstacionamentoDto updateEstacionamento(Long estacionamentoId, UpdateEstacionamentoDto dto) {
        Estacionamento estacionamento = buscarEstacionamento(estacionamentoId);

        validarNumeros(dto.andares(), dto.areaTotal(), dto.capacidade());

        if (dto.nome() != null) {
            if (dto.nome().isBlank()) {
                throw erro(HttpStatus.BAD_REQUEST, "O nome do estacionamento não pode ficar em branco.");
            }
            estacionamento.setNome(dto.nome());
        }
        if (dto.endereco() != null) {
            atualizarEndereco(estacionamento, dto.endereco());
        }
        if (dto.tipo() != null) {
            estacionamento.setTipo(converterTipo(dto.tipo()));
        }
        if (dto.coberto() != null) {
            estacionamento.setCoberto(dto.coberto());
        }
        if (dto.andares() != null) {
            estacionamento.setAndares(dto.andares());
        }
        if (dto.areaTotal() != null) {
            estacionamento.setAreaTotal(dto.areaTotal());
        }
        if (dto.capacidade() != null) {
            int veiculosEstacionados = estacionamento.getVeiculos().size();
            if (dto.capacidade() < veiculosEstacionados) {
                throw erro(HttpStatus.CONFLICT, "A capacidade não pode ser menor que a quantidade de veículos estacionados ("
                        + veiculosEstacionados + ").");
            }
            estacionamento.setCapacidade(dto.capacidade());
        }

        return estacionamentoMapper.toRecoveryEstacionamentoDto(estacionamentoRepository.save(estacionamento));
    }

    @Transactional(readOnly = true)
    public List<RecoveryEstacionamentoDto> getEstacionamentos() {
        return estacionamentoRepository.findAll().stream()
                .map(estacionamentoMapper::toRecoveryEstacionamentoDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public RecoveryEstacionamentoDto getEstacionamentoById(Long estacionamentoId) {
        return estacionamentoMapper.toRecoveryEstacionamentoDto(buscarEstacionamento(estacionamentoId));
    }

    @Transactional(readOnly = true)
    public List<RecoveryEstacionamentoDto> getEstacionamentosByTipo(String tipo) {
        return estacionamentoRepository.findByTipo(converterTipo(tipo)).stream()
                .map(estacionamentoMapper::toRecoveryEstacionamentoDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<RecoveryEstacionamentoDto> getEstacionamentosLotados() {
        return estacionamentoRepository.findLotados().stream()
                .map(estacionamentoMapper::toRecoveryEstacionamentoDto)
                .toList();
    }

    @Transactional
    public void deleteEstacionamentoById(Long estacionamentoId) {
        if (!estacionamentoRepository.existsById(estacionamentoId)) {
            throw erro(HttpStatus.NOT_FOUND, "Estacionamento não encontrado.");
        }
        estacionamentoRepository.deleteById(estacionamentoId);
    }

    private Estacionamento buscarEstacionamento(Long estacionamentoId) {
        return estacionamentoRepository.findById(estacionamentoId)
                .orElseThrow(() -> erro(HttpStatus.NOT_FOUND, "Estacionamento não encontrado."));
    }

    private void atualizarEndereco(Estacionamento estacionamento, EnderecoDto dto) {
        Endereco endereco = estacionamento.getEndereco();
        if (endereco == null) {
            estacionamento.setEndereco(estacionamentoMapper.toEndereco(dto));
            return;
        }
        if (dto.pais() != null) {
            endereco.setPais(dto.pais());
        }
        if (dto.estado() != null) {
            endereco.setEstado(dto.estado());
        }
        if (dto.cidade() != null) {
            endereco.setCidade(dto.cidade());
        }
        if (dto.bairro() != null) {
            endereco.setBairro(dto.bairro());
        }
        if (dto.rua() != null) {
            endereco.setRua(dto.rua());
        }
    }

    private void validarNumeros(Integer andares, Double areaTotal, Integer capacidade) {
        if (andares != null && andares < 1) {
            throw erro(HttpStatus.BAD_REQUEST, "O número de andares deve ser maior que zero.");
        }
        if (areaTotal != null && areaTotal <= 0) {
            throw erro(HttpStatus.BAD_REQUEST, "A área total deve ser maior que zero.");
        }
        if (capacidade != null && capacidade < 1) {
            throw erro(HttpStatus.BAD_REQUEST, "A capacidade deve ser maior que zero.");
        }
    }

    private TipoEstacionamento converterTipo(String tipo) {
        if (tipo == null || tipo.isBlank()) {
            throw erro(HttpStatus.BAD_REQUEST, "O tipo do estacionamento é obrigatório.");
        }
        try {
            return TipoEstacionamento.valueOf(tipo.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw erro(HttpStatus.BAD_REQUEST, "Tipo de estacionamento inválido: " + tipo
                    + ". Valores aceitos: " + Arrays.toString(TipoEstacionamento.values()));
        }
    }

    private ResponseStatusException erro(HttpStatus status, String mensagem) {
        return new ResponseStatusException(status, mensagem);
    }
}
