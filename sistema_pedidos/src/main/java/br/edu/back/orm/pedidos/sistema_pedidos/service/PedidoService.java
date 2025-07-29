package br.edu.back.orm.pedidos.sistema_pedidos.service;

import org.springframework.stereotype.Service;

import br.edu.back.orm.pedidos.sistema_pedidos.domain.Pedido;
import br.edu.back.orm.pedidos.sistema_pedidos.repository.PedidoRepository;

@Service
public class PedidoService {

    private PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository){
        this.pedidoRepository = pedidoRepository;
    }

    public void cancelarPedido(Integer pedidoId){
        Pedido pedido = pedidoRepository.findById(pedidoId);

        if(pedido.getStatus().equalsIgnoreCase("cancelado")){
            throw new IllegalStateException("Não pode cancelar mais de uma vez...")
        }
        
        pedido.setStatus("Cancelado");

    }


    
}
