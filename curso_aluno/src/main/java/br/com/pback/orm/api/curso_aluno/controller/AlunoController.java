package br.com.pback.orm.api.curso_aluno.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.pback.orm.api.curso_aluno.domain.Aluno;
import br.com.pback.orm.api.curso_aluno.dto.ErroDTO;

@Controller
@RequestMapping("/aluno")
public class AlunoController {

    List<Aluno> listaAlunos = new ArrayList<>();
    
    @GetMapping
    public ResponseEntity<?> listar (){
        if (listaAlunos.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ErroDTO("Não há nenhum aluno cadastro no sistema", LocalDateTime.now()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(listaAlunos);
    }

    @GetMapping("/{ra}")
    public ResponseEntity<?> buscaPorRa (@PathVariable String ra){
        if (listaAlunos.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ErroDTO("Não há nenhum aluno cadastro no sistema", LocalDateTime.now()));
        }
        for (Aluno index : listaAlunos) {
            if (index.getRa().equals(ra)){
                return ResponseEntity.status(HttpStatus.OK).body(index);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ErroDTO("Número de matrícula informado não encontrado", LocalDateTime.now()));
    }

    @PostMapping
    public ResponseEntity<?> cadastrarAluno (@RequestBody Aluno novoAluno){
        boolean existe = false;
        for (Aluno index : listaAlunos) {
            if (index.getRa().equals(novoAluno.getRa())){
                existe = true;
                break;
            }
        }

        if (existe){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new ErroDTO("Já existe um aluno com esse RA informado", LocalDateTime.now())
            );
        }

        listaAlunos.add(novoAluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAluno);
    }

    @PutMapping("/{ra}")
    public ResponseEntity<?> atualizar (@PathVariable String ra, @RequestBody Aluno alunoAtualizado){
        for (Aluno index : listaAlunos) {
            if (index.getRa().equals(ra)){
                index.setNome(alunoAtualizado.getNome());
                index.setEmail(alunoAtualizado.getEmail());
                index.setCursoSigla(alunoAtualizado.getCursoSigla());
                return ResponseEntity.ok(alunoAtualizado);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ErroDTO("Número de matrícula informado não foi encontrado", LocalDateTime.now()));
    }

    @DeleteMapping("/{ra}")
    public ResponseEntity<?> apagarAluno (@PathVariable String ra){
        for (Aluno index : listaAlunos) {
            if (index.getRa().equals(ra)) {
                listaAlunos.clear();
                return ResponseEntity.status(HttpStatus.OK).body(
                    new ErroDTO("Aluno deletado", LocalDateTime.now()));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ErroDTO("Número de matrícula informado não foi encontrado", LocalDateTime.now()));
    }


}
