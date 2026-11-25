package com.example.demo.models;

import jakarta.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
Endereço é uma classe @Embeddable: não vira uma tabela própria,
os campos dela são gravados como colunas na tabela "estacionamentos".
 */
@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Endereco {

    private String pais;

    private String estado;

    private String cidade;

    private String bairro;

    private String rua;
}
