package br.edu.iftm.tspi.porm.sistema_jpa.mapper;

import br.edu.iftm.tspi.porm.sistema_jpa.domain.Pedido;
import br.edu.iftm.tspi.porm.sistema_jpa.dto.PedidoDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-15T20:52:14-0300",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.42.0.v20250514-1000, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class PedidoMapperImpl implements PedidoMapper {

    @Override
    public PedidoDto toDto(Pedido pedido) {
        if ( pedido == null ) {
            return null;
        }

        PedidoDto.PedidoDtoBuilder pedidoDto = PedidoDto.builder();

        pedidoDto.dataPedido( pedido.getDataPedido() );
        pedidoDto.id( pedido.getId() );

        return pedidoDto.build();
    }

    @Override
    public Pedido toEntity(PedidoDto dto) {
        if ( dto == null ) {
            return null;
        }

        Pedido.PedidoBuilder pedido = Pedido.builder();

        pedido.dataPedido( dto.getDataPedido() );
        pedido.id( dto.getId() );

        return pedido.build();
    }

    @Override
    public List<PedidoDto> toDtoList(List<Pedido> pedidos) {
        if ( pedidos == null ) {
            return null;
        }

        List<PedidoDto> list = new ArrayList<PedidoDto>( pedidos.size() );
        for ( Pedido pedido : pedidos ) {
            list.add( toDto( pedido ) );
        }

        return list;
    }

    @Override
    public List<Pedido> toEntityList(List<PedidoDto> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<Pedido> list = new ArrayList<Pedido>( dtos.size() );
        for ( PedidoDto pedidoDto : dtos ) {
            list.add( toEntity( pedidoDto ) );
        }

        return list;
    }
}
