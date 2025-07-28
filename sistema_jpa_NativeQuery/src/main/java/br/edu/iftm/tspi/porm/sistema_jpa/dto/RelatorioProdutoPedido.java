package br.edu.iftm.tspi.porm.sistema_jpa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RelatorioProdutoPedido {
    
    private Integer ProdutoID;
    private Integer PedidoID;
    private Short quantidade;
    private Double precoTotal;
    private Double descontoTotal;
}
