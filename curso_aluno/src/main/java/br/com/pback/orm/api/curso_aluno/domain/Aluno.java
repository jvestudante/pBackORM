package br.com.pback.orm.api.curso_aluno.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Aluno {
    private String ra;
    private String nome;
    private String email;
    private String cursoSigla;
}
