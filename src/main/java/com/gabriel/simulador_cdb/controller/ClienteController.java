package com.gabriel.simulador_cdb.controller;

import com.gabriel.simulador_cdb.controller.request.BloqueioRequest;
import com.gabriel.simulador_cdb.controller.request.ClienteRequest;
import com.gabriel.simulador_cdb.controller.request.ClienteUpdateRequest;
import com.gabriel.simulador_cdb.controller.response.BloqueioResponse;
import com.gabriel.simulador_cdb.controller.response.ClienteResponse;
import com.gabriel.simulador_cdb.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping("/cadastrar")
    public ClienteResponse cadastar(@Valid @RequestBody ClienteRequest request){
        return clienteService.cadastrar(request);
    }

    @GetMapping("/{id}")
    public ClienteResponse lsitarPorId(@PathVariable Long id){
        return clienteService.listar(id);
    }

    @PutMapping("/{id}")
    public ClienteResponse atualizar(@PathVariable Long id, @RequestBody ClienteUpdateRequest clienteUpdateRequest){
        return clienteService.atualizar(id, clienteUpdateRequest);
    }

    @PostMapping("/bloquear/{idCliente}")
    public BloqueioResponse bloquearCliente(@PathVariable Long idCliente, @Valid BloqueioRequest bloqueioRequest){
        return clienteService.bloquear(idCliente, bloqueioRequest);
    }

    @PatchMapping("/desbloquear/{idCliente}")
    public BloqueioResponse bloquearCliente(@PathVariable Long idCliente){
        return clienteService.desbloquear(idCliente);
    }

}
