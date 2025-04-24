package br.com.orm.matematica.matematica.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Matriz {
    private List<List<Integer>> valor;

}