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
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.pback.orm.api.curso_aluno.domain.Curso;
import br.com.pback.orm.api.curso_aluno.domain.Aluno;
import br.com.pback.orm.api.curso_aluno.dto.ErroDTO;

@Controller
@RequestMapping("/curso")
public class CursoController {
    
    List<Curso> listaCursos = new ArrayList<>();
    
    @GetMapping
    public ResponseEntity<?> listaCurso (){
        if (listaCursos.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ErroDTO("Não cursos cadastrados", LocalDateTime.now()));
        }
        return ResponseEntity.ok().body(listaCursos);
    }

    @GetMapping("/{sigla}")
    public ResponseEntity<?> listaPorSigla (@PathVariable String sigla){
        if (listaCursos.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ErroDTO("Não cursos cadastrados", LocalDateTime.now()));
        }
        for (Curso index : listaCursos) {
            if (index.getSigla().equals(sigla)){
                return ResponseEntity.ok().body(index);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ErroDTO("Não há curso com a sigla informada", LocalDateTime.now()));
    }

    @GetMapping("/{sigla}/alunos")
    public ResponseEntity<?> listaAlunoCurso (@PathVariable String sigla){
        List<Aluno> novosAlunos = new ArrayList<>();
        Aluno novo = new Aluno("ra01", "Maria Fátima", "maria@email.com", "ads");
        novosAlunos.add(novo);
        
        for (Aluno aluno : novosAlunos) {
            if (aluno.getCursoSigla().equals(sigla)){
                return ResponseEntity.ok().body(aluno);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ErroDTO("Aluno não encontrado", LocalDateTime.now()));
    }

    @PostMapping
    public ResponseEntity<?> criaCurso (@RequestBody Curso novoCurso){
        boolean existe = false;
        for (Curso index : listaCursos) {
            if (index.getSigla().equals(novoCurso.getSigla())){
                existe = true;
                break;
            }
        }
        if (existe){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new ErroDTO("Já existe um curso com essa sigla cadastrado", LocalDateTime.now())
            );
        }
        listaCursos.add(novoCurso);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCurso);
    }

    @PutMapping("/{sigla}")
    public ResponseEntity<?> atualizarCurso (@PathVariable String sigla, @RequestBody Curso cursoAtualizado){
        for (Curso index : listaCursos) {
            if (index.getSigla().equals(sigla)){
                index.setNome()(cursoAtualizado.getNome());
                index.setDescricao(cursoAtualizado.getDescricao());
                return ResponseEntity.ok(cursoAtualizado);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ErroDTO("Sigla de curso informado não foi encontrado", LocalDateTime.now()));
    }

    @DeleteMapping("/{sigla}")
    public ResponseEntity<?> apagarCurso (@PathVariable String sigla){
        for (Curso index : listaCursos) {
            if (index.getSigla().equals(sigla)) {
                listaCursos.remove(index);
                return ResponseEntity.status(HttpStatus.OK).body(
                    new ErroDTO("Curso deletado", LocalDateTime.now()));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ErroDTO("Sigla de curso informado não foi encontrado", LocalDateTime.now()));
    }

}
