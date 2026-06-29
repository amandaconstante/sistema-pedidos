package com.app.sistema_pedidos.controller;

import com.app.sistema_pedidos.dto.pedido.DadosProduto;
import com.app.sistema_pedidos.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    private final ProdutoRepository produtoRepository;

    @GetMapping
    public ResponseEntity<Page<DadosProduto>> listar(@PageableDefault(sort = {"nome"}) Pageable pageable) {
        var page = produtoRepository.findAll(pageable).map(DadosProduto::new);
        return ResponseEntity.ok(page);
    }
}
