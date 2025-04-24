
package br.com.orm.matematica.matematica.Controller;

import java.security.DrbgParameters.Reseed;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.orm.matematica.matematica.DTO.ErroDTO;
import br.com.orm.matematica.matematica.DTO.matematicaDTO;
import br.com.orm.matematica.matematica.domain.Matriz;
import br.com.orm.matematica.matematica.domain.Media;

@Controller
@RequestMapping("/matematica")
public class matematicaController {


    @GetMapping("/soma")
    public ResponseEntity<?> somar (@RequestParam Integer a, @RequestParam Integer b){
        if (a%1 != 0 || b%1 != 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ErroDTO("Não é número inteiro"));
        } 
        int resultado = a+b;
        return ResponseEntity.status(HttpStatus.OK).body(
            new matematicaDTO(resultado));
    }

    @GetMapping("/fatorial")
    public ResponseEntity<?> fatorial (@RequestParam Integer n){
        if (n<0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ErroDTO("Não é aceito número negativo"));
        }
        int resultado = 1;
        for (int i=1; i<=5; i++ ){
            resultado = resultado*i;
        }

        return ResponseEntity.status(HttpStatus.OK).body(
            new matematicaDTO(resultado));
    }

    @PostMapping("/media")
    public ResponseEntity<?> calculaMedia (@RequestBody Media media){
        
        if (media.getValores() == null || media.getValores().isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ErroDTO("Lista de números vazia"));
        }
        double soma = 0;
        for (Double index : media.getValores()){
            soma = soma+index;
        }
        double resultado = soma/media.getValores().size();
        return ResponseEntity.ok(resultado);
    }

    @PostMapping("/soma-linhas")
    public ResponseEntity<?> calculaMatriz (@RequestBody Matriz matriz){
        if (matriz.getValor() == null || matriz.getValor().isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ErroDTO("Matriz vazia ou nula"));
        }
        return ResponseEntity.ok(matriz);
    }
}