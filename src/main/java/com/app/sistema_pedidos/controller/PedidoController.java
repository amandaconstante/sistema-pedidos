package com.app.sistema_pedidos.controller;

import com.app.sistema_pedidos.dto.pedido.DadosCadastroPedido;
import com.app.sistema_pedidos.dto.pedido.DadosDetalhePedido;
import com.app.sistema_pedidos.dto.pedido.DadosPedido;
import com.app.sistema_pedidos.infra.exception.ValidacaoException;
import com.app.sistema_pedidos.repository.PedidoRepository;
import com.app.sistema_pedidos.service.PedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    private final PedidoService pedidoService;
    private final PedidoRepository pedidoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroPedido dados) {
        var dto = pedidoService.cadastrarPedido(dados);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<DadosPedido>> listar(
            @RequestParam(required = false) String nomeCliente,
            @RequestParam(required = false) LocalDate dataInicio,
            @RequestParam(required = false) LocalDate dataFim,
            @PageableDefault(size = 10, sort = {"dataCriacao"}) Pageable pageable
            ) {
        LocalDateTime inicio = dataInicio != null ? dataInicio.atStartOfDay() : null;
        LocalDateTime fim = dataFim != null ? dataFim.atTime(23,59,59) : null;

        String nomeFiltro = nomeCliente != null ? "%" + nomeCliente + "%" : null;

        var page = pedidoRepository.buscarComFiltros(nomeFiltro, inicio, fim, pageable).map(DadosPedido::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhePedido> detalhar(@PathVariable Long id) {
        var pedido = pedidoRepository.findById(id).orElseThrow(() -> new ValidacaoException("Pedido não encontrado!"));
        return ResponseEntity.ok(new DadosDetalhePedido(pedido));
    }
}
