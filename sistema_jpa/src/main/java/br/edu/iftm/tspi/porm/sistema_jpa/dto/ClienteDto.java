package br.edu.iftm.tspi.porm.sistema_jpa.dto;

import java.util.List;

import br.edu.iftm.tspi.porm.sistema_jpa.domain.Pedido;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDto {
    
    private String id;

    @NotBlank(message = "Nome do cliente é obrigatório")
    private String nome;

    private String cargo;

    @NotBlank(message = "Endereço é um campo obrigatório.")
    private String endereco;

    @NotBlank(message = "CEP é um campo obrigatório.")
    private String cep;

    @NotBlank(message = "Pais é um campo obrigatório.")
    private String pais;

    private String telefone;

    private String fax;

    private List<Pedido> pedidos;
}
