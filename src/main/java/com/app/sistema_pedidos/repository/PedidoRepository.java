package com.app.sistema_pedidos.repository;

import com.app.sistema_pedidos.domain.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query("SELECT p FROM Pedido p WHERE " +
            "(:nomeCliente IS NULL OR LOWER(p.cliente.nome) LIKE LOWER(CAST(:nomeCliente AS String))) AND " +
            "(CAST(:dataInicio AS timestamp) IS NULL OR p.dataCriacao >= :dataInicio) AND " +
            "(CAST(:dataFim AS timestamp) IS NULL OR p.dataCriacao <= :dataFim)")
    Page<Pedido> buscarComFiltros(@Param("nomeCliente") String nomeCliente,
                                  @Param("dataInicio") LocalDateTime dataInicio,
                                  @Param("dataFim") LocalDateTime dataFim,
                                  Pageable pageable);
}
