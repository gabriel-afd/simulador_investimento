package com.gabriel.simulador_cdb.mapper;

import com.gabriel.simulador_cdb.controller.request.ClienteRequest;
import com.gabriel.simulador_cdb.controller.request.ClienteUpdateRequest;
import com.gabriel.simulador_cdb.controller.response.BloqueioResponse;
import com.gabriel.simulador_cdb.controller.response.ClienteResponse;
import com.gabriel.simulador_cdb.entity.BloqueioCliente;
import com.gabriel.simulador_cdb.entity.Cliente;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ClienteMapper {
    public Cliente toEntity(ClienteRequest request) {
        Cliente cliente = new Cliente();

        cliente.setNome(request.getNome());
        cliente.setCpf(request.getCpf());
        cliente.setEmail(request.getEmail());
        cliente.setSenhaHash(request.getSenhaHash());
        cliente.setDataNascimento(request.getDataNascimento());
        cliente.setDataCadastro(LocalDate.now());

        return  cliente;
    }

    public ClienteResponse toResponse(Cliente clienteSalvo) {
        ClienteResponse clienteResponse = new ClienteResponse();
        clienteResponse.setId(clienteSalvo.getIdCliente());
        clienteResponse.setNome(clienteSalvo.getNome());
        clienteResponse.setEmail(clienteSalvo.getEmail());

        return clienteResponse;
    }


    public void updateEntityFromRequestUpdate(Cliente cliente, ClienteUpdateRequest updateRequest) {
        cliente.setNome(updateRequest.getNome());
        cliente.setEmail(updateRequest.getEmail());
    }

    public BloqueioResponse toResponseBloqueio(BloqueioCliente bloqueioCliente) {

        BloqueioResponse bloqueioResponse = new BloqueioResponse();

        bloqueioResponse.setIdBloqueio(bloqueioCliente.getIdBloqueio());
        bloqueioResponse.setDataInicio(bloqueioCliente.getDataInicio());
        bloqueioResponse.setDataFim(bloqueioCliente.getDataFim());
        bloqueioResponse.setStatusBloqueio(bloqueioCliente.getStatusBloqueio());

        return bloqueioResponse;
    }
}
