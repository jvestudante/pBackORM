package br.com.pback.orm.api.curso_aluno.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Curso {
    private String sigla;
    private String nome;
    private String descricao;
}
