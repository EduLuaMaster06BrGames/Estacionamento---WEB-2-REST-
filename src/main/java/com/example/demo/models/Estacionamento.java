package com.example.demo.models;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "estacionamentos")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Estacionamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idestacionamento")
    private Long id;

    private String nome;

    @Embedded
    private Endereco endereco;

    @Enumerated(EnumType.STRING)
    private TipoEstacionamento tipo;

    private Boolean coberto;

    private Integer andares;

    private Double areaTotal;

    private Integer capacidade;

    @OneToMany(mappedBy = "estacionamento", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Veiculo> veiculos = new ArrayList<>();
}