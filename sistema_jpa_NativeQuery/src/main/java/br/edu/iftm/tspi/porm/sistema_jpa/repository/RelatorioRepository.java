package br.edu.iftm.tspi.porm.sistema_jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.iftm.tspi.porm.sistema_jpa.domain.Produto;
import br.edu.iftm.tspi.porm.sistema_jpa.dto.RelatorioConsumoPorCategoriaDto;
import br.edu.iftm.tspi.porm.sistema_jpa.dto.RelatorioConsumoPorClienteDto;
import br.edu.iftm.tspi.porm.sistema_jpa.dto.RelatorioProdutoClienteDto;
import br.edu.iftm.tspi.porm.sistema_jpa.dto.RelatorioProdutoPedido;
import br.edu.iftm.tspi.porm.sistema_jpa.dto.RelatorioProdutoPedidoDto;

@Repository
public interface RelatorioRepository extends JpaRepository<Produto, Long> {
    
    @Query(nativeQuery = true,value= """
            select c.nome as nomeCliente,
                   pr.produtoNome as nomeProduto,
                   sum((dp.precoVenda * quantidade) - desconto) as valor
            from pedidos p, detalhes_pedido dp, clientes c,produtos pr
            where p.pedidoId = dp.pedidoId
              and p.clienteId = c.clienteId
              and dp.produtoId = pr.produtoId
              and c.clienteID = :clienteID
            group by c.nome,pr.produtoNome,p.pedidoId
          """)
    List<RelatorioProdutoClienteDto> getProdutosPorCliente(@Param("clienteID") String clienteID);


     @Query(nativeQuery = true,value= """
            select 
                   pr.produtoNome as nomeProduto,
                   sum((dp.precoVenda * quantidade) - desconto) as valor
            from detalhes_pedido dp, produtos pr
            where  dp.produtoId = pr.produtoId
              and dp.pedidoID = :pedidoID
            group by pr.produtoNome
          """)
    List<RelatorioProdutoPedidoDto> getProdutosPorPedido(@Param("pedidoID") Integer numeroPedido);

    @Query(nativeQuery = true, value = """
                select d.pedidoid,
                       d.quantidade,
                       (d.precoVenda*d.quantidade) as precoTotal,
                       (d.desconto*d.quantidade) as descontoTotal
                from detalhes_pedido d, produtos p
                where p.produtoid = :idProduto
                """)
    List<RelatorioProdutoPedidoDto> getResumoVendaPorProduto(@Param("idProduto") Integer id);

    
    @Query(nativeQuery = true, value = """
              select dp.produtoId as produtoId,
              SUM((dp.precoVenda * dp.quantidade) - dp.desconto) as totalConsumido
              from detalhes_pedido dp
              join pedidos p on dp.pedidoId = p.pedidoId
              where p.clienteId = :clienteId
              group by dp.produtoId
              """)
    List<RelatorioConsumoPorClienteDto> getTotalConsumidoPorProdutoByClienteId(@Param("clienteId") String clienteId);


    @Query(nativeQuery = true, value = """
              select p.categoriaId as categoriaId,
              SUM((dp.precoVenda * dp.quantidade) - dp.desconto) as totalConsumido
              from detalhes_pedido dp
              join produtos p on dp.produtoId = p.produtoId
              where p.categoriaId = :categoriaId
              group by p.categoriaId
              """)
    RelatorioConsumoPorCategoriaDto getTotalConsumidoByCategoriaId(@Param("categoriaId") Integer categoriaId);


}
