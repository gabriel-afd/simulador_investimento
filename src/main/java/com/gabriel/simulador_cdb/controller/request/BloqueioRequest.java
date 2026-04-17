package com.gabriel.simulador_cdb.controller.request;

import com.gabriel.simulador_cdb.entity.StatusBloqueio;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BloqueioRequest {

    @NotNull
    private LocalDate dataInicio;
    private LocalDate dataFim;
}
