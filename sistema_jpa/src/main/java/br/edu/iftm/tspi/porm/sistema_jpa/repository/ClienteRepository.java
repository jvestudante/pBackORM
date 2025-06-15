    package br.edu.iftm.tspi.porm.sistema_jpa.repository;

    import org.springframework.data.jpa.repository.JpaRepository;
    import br.edu.iftm.tspi.porm.sistema_jpa.domain.Cliente;


    public interface ClienteRepository extends JpaRepository<Cliente, String>{
        
    }
