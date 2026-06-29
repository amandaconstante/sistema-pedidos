package com.app.sistema_pedidos.controller;

import com.app.sistema_pedidos.domain.Cliente;
import com.app.sistema_pedidos.dto.cliente.DadosCadastroCliente;
import com.app.sistema_pedidos.dto.cliente.DadosCliente;
import com.app.sistema_pedidos.dto.cliente.DadosResponseCliente;
import com.app.sistema_pedidos.repository.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository repository) {
        this.clienteRepository = repository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroCliente dados, UriComponentsBuilder uriBuilder) {
        var cliente = new Cliente(dados);
        clienteRepository.save(cliente);
        var uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosResponseCliente(cliente));
    }

    @GetMapping
    public ResponseEntity<Page<DadosCliente>> listar(@PageableDefault(size = 4, sort = {"nome"}) Pageable pageable) {
        var page = clienteRepository.findAll(pageable).map(DadosCliente::new);
        return ResponseEntity.ok(page);
    }

}
