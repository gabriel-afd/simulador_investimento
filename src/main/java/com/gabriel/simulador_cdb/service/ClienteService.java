package com.gabriel.simulador_cdb.service;

import com.gabriel.simulador_cdb.controller.request.BloqueioRequest;
import com.gabriel.simulador_cdb.controller.request.ClienteRequest;
import com.gabriel.simulador_cdb.controller.request.ClienteUpdateRequest;
import com.gabriel.simulador_cdb.controller.response.BloqueioResponse;
import com.gabriel.simulador_cdb.controller.response.ClienteResponse;
import com.gabriel.simulador_cdb.entity.BloqueioCliente;
import com.gabriel.simulador_cdb.entity.Cliente;
import com.gabriel.simulador_cdb.entity.Conta;
import com.gabriel.simulador_cdb.entity.StatusBloqueio;
import com.gabriel.simulador_cdb.exception.BusinessException;
import com.gabriel.simulador_cdb.exception.ResourceNotFoundException;
import com.gabriel.simulador_cdb.mapper.ClienteMapper;
import com.gabriel.simulador_cdb.repository.BloqueioRepository;
import com.gabriel.simulador_cdb.repository.ClienteRepository;
import com.gabriel.simulador_cdb.repository.ContaRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ContaRepository contaRepository;
    private final BloqueioRepository bloqueioRepository;
    private final PasswordEncoder passwordEncoder;
    private final ClienteMapper clienteMapper;

    @Transactional
    public ClienteResponse cadastrar(ClienteRequest request) {
        if (clienteRepository.existsByCpf(request.getCpf())){
            throw  new BusinessException("CPF já existe!");
        }

        if (clienteRepository.existsByEmail(request.getEmail())){
            throw  new BusinessException("Email já existe!");
        }

        Cliente cliente = clienteMapper.toEntity(request);
        cliente.setSenhaHash(passwordEncoder.encode(cliente.getSenhaHash()));

        Cliente clienteSalvo = clienteRepository.save(cliente);

        Conta conta = new Conta();
        conta.setCliente(clienteSalvo);
        conta.setSaldoDisponivel(BigDecimal.ZERO);
        conta.setDataAbertura(LocalDate.now());
        conta.setAtivo(true);

        contaRepository.save(conta);

        return clienteMapper.toResponse(clienteSalvo);

    }

    @Transactional
    public ClienteResponse atualizar(Long id, ClienteUpdateRequest updateRequest) {

        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));

        if (!cliente.getEmail().equals(updateRequest.getEmail()) && clienteRepository.existsByEmail(updateRequest.getEmail())){
            throw new BusinessException("Email já está em uso!");
        }

        //entidade -> requestUpdate
        clienteMapper.updateEntityFromRequestUpdate(cliente, updateRequest);

        clienteRepository.save(cliente);

        return clienteMapper.toResponse(cliente);
    }

    public ClienteResponse listar(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));

        return clienteMapper.toResponse(cliente);
    }

    public BloqueioResponse bloquear(Long idCliente,BloqueioRequest bloqueioRequest) {

        Cliente cliente = clienteRepository.findByIdCliente(idCliente)
                .orElseThrow(() -> new ResourceNotFoundException("Bloqueio para cliente não encontrado"));

        BloqueioCliente bloqueioCliente = cliente.getBloqueioCliente();

        if ((bloqueioCliente != null) && bloqueioCliente.getStatusBloqueio() == StatusBloqueio.ATIVO){
            throw new BusinessException("Cliente já está bloqueado");
        }

        if (bloqueioCliente == null) {
            bloqueioCliente = new BloqueioCliente();
            bloqueioCliente.setCliente(cliente);
        }

        bloqueioCliente.setStatusBloqueio(StatusBloqueio.ATIVO);
        bloqueioCliente.setDataInicio(LocalDate.now());
        bloqueioCliente.setDataFim(bloqueioRequest.getDataFim());

        bloqueioRepository.save(bloqueioCliente);

        return clienteMapper.toResponseBloqueio(bloqueioCliente);
    }

    public BloqueioResponse desbloquear(Long idCliente) {

        BloqueioCliente bloqueioCliente = bloqueioRepository.findByCliente_IdCliente(idCliente)
                .orElseThrow(() -> new ResourceNotFoundException("Bloqueio para cliente não encontrado"));

        if (bloqueioCliente.getStatusBloqueio() == StatusBloqueio.ENCERRADO){
            throw new BusinessException("Cliente já está desbloqueado");
        }

        bloqueioCliente.setStatusBloqueio(StatusBloqueio.ENCERRADO);
        bloqueioCliente.setDataFim(LocalDate.now());

        bloqueioRepository.save(bloqueioCliente);

        return clienteMapper.toResponseBloqueio(bloqueioCliente);
    }
}
