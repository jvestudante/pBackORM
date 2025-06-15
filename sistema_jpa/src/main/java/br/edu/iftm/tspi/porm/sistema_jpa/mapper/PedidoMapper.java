package br.edu.iftm.tspi.porm.sistema_jpa.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import br.edu.iftm.tspi.porm.sistema_jpa.domain.Pedido;
import br.edu.iftm.tspi.porm.sistema_jpa.dto.PedidoDto;

@Mapper(componentModel = "spring")
public interface PedidoMapper {

    PedidoDto toDto(Pedido pedido);

    Pedido toEntity(PedidoDto dto);

    List<PedidoDto> toDtoList(List<Pedido> pedidos);

    List<Pedido> toEntityList(List<PedidoDto> dtos);
}
