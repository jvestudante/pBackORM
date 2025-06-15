package br.edu.iftm.tspi.porm.sistema_jpa.mapper;

import br.edu.iftm.tspi.porm.sistema_jpa.domain.Cliente;
import br.edu.iftm.tspi.porm.sistema_jpa.domain.Pedido;
import br.edu.iftm.tspi.porm.sistema_jpa.dto.ClienteDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-15T20:51:33-0300",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.42.0.v20250514-1000, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class ClienteMapperImpl implements ClienteMapper {

    @Override
    public Cliente toEntity(ClienteDto dto) {
        if ( dto == null ) {
            return null;
        }

        Cliente.ClienteBuilder cliente = Cliente.builder();

        cliente.cargo( dto.getCargo() );
        cliente.cep( dto.getCep() );
        cliente.endereco( dto.getEndereco() );
        cliente.fax( dto.getFax() );
        cliente.id( dto.getId() );
        cliente.nome( dto.getNome() );
        cliente.pais( dto.getPais() );
        List<Pedido> list = dto.getPedidos();
        if ( list != null ) {
            cliente.pedidos( new ArrayList<Pedido>( list ) );
        }
        cliente.telefone( dto.getTelefone() );

        return cliente.build();
    }

    @Override
    public ClienteDto toDto(Cliente entity) {
        if ( entity == null ) {
            return null;
        }

        ClienteDto.ClienteDtoBuilder clienteDto = ClienteDto.builder();

        clienteDto.cargo( entity.getCargo() );
        clienteDto.cep( entity.getCep() );
        clienteDto.endereco( entity.getEndereco() );
        clienteDto.fax( entity.getFax() );
        clienteDto.id( entity.getId() );
        clienteDto.nome( entity.getNome() );
        clienteDto.pais( entity.getPais() );
        List<Pedido> list = entity.getPedidos();
        if ( list != null ) {
            clienteDto.pedidos( new ArrayList<Pedido>( list ) );
        }
        clienteDto.telefone( entity.getTelefone() );

        return clienteDto.build();
    }

    @Override
    public List<ClienteDto> toDtoList(List<Cliente> entities) {
        if ( entities == null ) {
            return null;
        }

        List<ClienteDto> list = new ArrayList<ClienteDto>( entities.size() );
        for ( Cliente cliente : entities ) {
            list.add( toDto( cliente ) );
        }

        return list;
    }

    @Override
    public List<Cliente> toEntityList(List<ClienteDto> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<Cliente> list = new ArrayList<Cliente>( dtos.size() );
        for ( ClienteDto clienteDto : dtos ) {
            list.add( toEntity( clienteDto ) );
        }

        return list;
    }
}
