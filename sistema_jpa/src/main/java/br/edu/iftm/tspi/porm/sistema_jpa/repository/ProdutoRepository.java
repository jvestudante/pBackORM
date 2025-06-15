package br.edu.iftm.tspi.porm.sistema_jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.iftm.tspi.porm.sistema_jpa.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    List<Produto> findByNomeContainingIgnoreCase(String nome);
    
}
