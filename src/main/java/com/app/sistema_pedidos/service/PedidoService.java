package com.app.sistema_pedidos.service;

import com.app.sistema_pedidos.domain.ItemPedido;
import com.app.sistema_pedidos.domain.Pedido;
import com.app.sistema_pedidos.dto.pedido.DadosItemPedido;
import com.app.sistema_pedidos.dto.pedido.DadosPedido;
import com.app.sistema_pedidos.dto.pedido.DadosCadastroPedido;
import com.app.sistema_pedidos.infra.exception.ValidacaoException;
import com.app.sistema_pedidos.repository.ClienteRepository;
import com.app.sistema_pedidos.repository.PedidoRepository;
import com.app.sistema_pedidos.repository.ProdutoRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final ClienteRepository clienteRepository;

    public DadosPedido cadastrarPedido(@Valid DadosCadastroPedido dados) {
        var cliente = clienteRepository.findById(dados.clienteId()).orElseThrow(() -> new ValidacaoException("Cliente não encontrado"));
        if (dados.itens().isEmpty()) {
            throw new ValidacaoException("O pedido deve ter pelo menos um item");
        }
        var pedido = new Pedido(cliente);

        for(DadosItemPedido itemDto : dados.itens()) {
            var produto = produtoRepository.findById(itemDto.produtoId()).orElseThrow(() -> new ValidacaoException("Produto " + itemDto.produtoId() + " não encontrado!"));
            var item = new ItemPedido(produto, pedido, itemDto.quantidade(), produto.getPreco());
            pedido.adicionarItem(item);
        }
        pedidoRepository.save(pedido);
        return new DadosPedido(pedido);
    }
}
