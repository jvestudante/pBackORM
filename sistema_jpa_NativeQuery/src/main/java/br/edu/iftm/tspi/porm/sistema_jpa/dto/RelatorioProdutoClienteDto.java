package br.edu.iftm.tspi.porm.sistema_jpa.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RelatorioProdutoClienteDto {


    private String nomeCliente;
    private String nomeProduto;
    private Double valor;

}
