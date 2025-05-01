package br.com.pback.orm.api.curso_aluno.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErroDTO {
    private String mensagem;
    private LocalDateTime horario;
}
