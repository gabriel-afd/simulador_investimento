package com.gabriel.simulador_cdb.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "cliente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;
    private String cpf;
    private String nome;
    private LocalDate dataNascimento;
    private String email;
    private String senhaHash;
    private String perfil;
    private LocalDate dataCadastro;

    @OneToOne(mappedBy = "cliente", fetch = FetchType.LAZY)
    private Conta conta;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private List<Investimento> investimentos;

    @OneToOne(mappedBy = "cliente")
    private BloqueioCliente bloqueioCliente;
}
