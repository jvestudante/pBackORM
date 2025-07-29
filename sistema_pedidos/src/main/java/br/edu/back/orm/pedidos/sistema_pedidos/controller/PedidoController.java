package br.edu.back.orm.pedidos.sistema_pedidos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.back.orm.pedidos.sistema_pedidos.domain.Pedido;
import br.edu.back.orm.pedidos.sistema_pedidos.repository.PedidoRepository;
import br.edu.back.orm.pedidos.sistema_pedidos.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    
    private PedidoRepository pedidoRepository;
    private PedidoService pedidoService;

    public PedidoController(PedidoRepository pedidoRepository, PedidoService pedidoService){
        this.pedidoRepository = pedidoRepository;
        this.pedidoService = pedidoService;
    }

    @PostMapping("/inserir")
    public ResponseEntity<Pedido> inserir(@RequestBody Pedido pedido) {
        Pedido salvo = pedidoRepository.save(pedido);
        return ResponseEntity.ok(salvo);
    }

    @PostMapping("/{id}/cancelar")
    public ResponseEntity<String> cancelarPedido(@PathVariable Integer id) {
        // pedidoService.cancelarPedido(id);
        return ResponseEntity.ok("Cancelou");
    }
}
