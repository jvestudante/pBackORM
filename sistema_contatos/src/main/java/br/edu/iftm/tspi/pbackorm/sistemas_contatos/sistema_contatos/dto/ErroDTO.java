package br.edu.iftm.tspi.pbackorm.sistemas_contatos.sistema_contatos.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErroDTO {
    
    private String mensagem;
    private Integer idConflito;
    private LocalDateTime data;
}
