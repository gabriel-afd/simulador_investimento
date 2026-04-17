package com.gabriel.simulador_cdb.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "bloqueio_cliente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BloqueioCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBloqueio;

    @OneToOne
    @JoinColumn(name = "id_cliente", nullable = false, unique = true)
    private Cliente cliente;

    private LocalDate dataInicio;
    private LocalDate dataFim;

    @Enumerated(EnumType.STRING)
    private StatusBloqueio statusBloqueio;
}
