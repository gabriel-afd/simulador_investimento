package com.gabriel.simulador_cdb.controller.response;

import com.gabriel.simulador_cdb.entity.Cliente;
import com.gabriel.simulador_cdb.entity.StatusBloqueio;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BloqueioResponse {

    private Long idBloqueio;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private StatusBloqueio statusBloqueio;
}
