package br.edu.iftm.tspi.porm.sistema_jpa.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iftm.tspi.porm.sistema_jpa.dto.RelatorioConsumoPorCategoriaDto;
import br.edu.iftm.tspi.porm.sistema_jpa.dto.RelatorioConsumoPorClienteDto;
import br.edu.iftm.tspi.porm.sistema_jpa.dto.RelatorioProdutoClienteDto;
import br.edu.iftm.tspi.porm.sistema_jpa.dto.RelatorioProdutoPedidoDto;
import br.edu.iftm.tspi.porm.sistema_jpa.repository.RelatorioRepository;


@RestController
@RequestMapping("/relatorios")
public class RelatorioController {

    private final RelatorioRepository repository;

    public RelatorioController(RelatorioRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/produto-por-cliente")
    public ResponseEntity<List<RelatorioProdutoClienteDto>> 
                getProdutosPorCliente(@RequestParam("clienteID") String clienteID) {
        List<RelatorioProdutoClienteDto> retorno = repository.getProdutosPorCliente(clienteID);
        if (retorno.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(retorno);
    }

    @GetMapping("/produto-por-pedido")
    public ResponseEntity<List<RelatorioProdutoPedidoDto>> 
                getProdutosPorPedido(@RequestParam("pedidoId") Integer pedidoId) {
        List<RelatorioProdutoPedidoDto> retorno = repository.getProdutosPorPedido(pedidoId);
        if (retorno.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(retorno);
    }

    @GetMapping("/resumo-venda-produto/{id}")
    public ResponseEntity<List<RelatorioProdutoPedidoDto>> getVendaPorProdutos (@PathVariable Integer id){
        List<RelatorioProdutoPedidoDto> resumo = repository.getProdutosPorPedido(id);
        if (resumo.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(resumo);
    }
    
    @GetMapping("/consumo-cliente/{clienteId}")
    public ResponseEntity<List<RelatorioConsumoPorClienteDto>> consumoPorCliente(@PathVariable String clienteId) {
        List<RelatorioConsumoPorClienteDto> lista = repository.getTotalConsumidoPorProdutoByClienteId(clienteId);
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/consumo-categoria/{categoriaId}")
    public ResponseEntity<RelatorioConsumoPorCategoriaDto> consumoPorCategoria(@PathVariable Integer categoriaId) {
    RelatorioConsumoPorCategoriaDto dto = repository.getTotalConsumidoByCategoriaId(categoriaId);
        if (dto == null || dto.getTotalConsumido() == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(dto);
}

}
