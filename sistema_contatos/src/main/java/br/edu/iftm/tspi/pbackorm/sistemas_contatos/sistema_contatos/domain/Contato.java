package br.edu.iftm.tspi.pbackorm.sistemas_contatos.sistema_contatos.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Contato {
    
    private Integer codigo;
    private String nome;
}
