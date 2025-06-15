package br.edu.iftm.tspi.porm.sistema_jpa.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDto {

    private Integer id;

    private LocalDateTime dataPedido;

}

