package com.example.demo.models;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "veiculos")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idveiculo")
    private Long id;

    private String modelo;

    private String placa;

    @Enumerated(EnumType.STRING)
    @Column(name="tipoVeiculo")
    private TipoVeiculo tipo;

    // Lado "N" da relação: muitos veículos pertencem a um estacionamento
    @ManyToOne
    @JoinColumn(name = "idestacionamento")
    private Estacionamento estacionamento;
}
