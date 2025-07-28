package br.edu.iftm.tspi.porm.sistema_jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.iftm.tspi.porm.sistema_jpa.domain.Produto;
import br.edu.iftm.tspi.porm.sistema_jpa.dto.ProdutoDto;
import br.edu.iftm.tspi.porm.sistema_jpa.dto.RelatorioProdutoPedido;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    List<Produto> findByNomeContainingIgnoreCase(String nome);

    @Query("""
                select p from Produto p
                join fetch p.categoria
                where p.preco = (
                    select max(p2.preco)
                    from Produto p2
                    where p2.categoria.id = p.categoria.id
            )
            """)
    List<Produto> findMaxPreco();

    
    @Query(nativeQuery=true,value = """
            SELECT p.produtoid,
                   p.produtonome,                   
                   p.preco,
                   p.unidadesemestoque,
                   p.imagem,                    
                   p.categoriaid
            FROM produtos p
            WHERE p.preco = (
            SELECT MAX(p2.preco)
            FROM produtos p2
            WHERE p2.categoriaid = p.categoriaid
            )
            """)
    List<ProdutoDto> findMaxPrecoNativo();

    
    
}
