package br.com.orm.matematica.matematica.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Media{
    private String txt;
    private List<Double> valores;
}
