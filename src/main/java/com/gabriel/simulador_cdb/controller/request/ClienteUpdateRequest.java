package com.gabriel.simulador_cdb.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteUpdateRequest {
    private String nome;
    private String email;
}
