package br.edu.iftm.tspi.porm.sistema_jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.iftm.tspi.porm.sistema_jpa.domain.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>{

    
}
