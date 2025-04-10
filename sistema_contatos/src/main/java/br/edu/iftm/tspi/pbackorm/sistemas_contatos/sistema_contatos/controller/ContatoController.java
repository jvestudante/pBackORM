package br.edu.iftm.tspi.pbackorm.sistemas_contatos.sistema_contatos.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iftm.tspi.pbackorm.sistemas_contatos.sistema_contatos.domain.Contato;
import br.edu.iftm.tspi.pbackorm.sistemas_contatos.sistema_contatos.dto.ErroDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/contatos")
public class ContatoController {

    private List<Contato> contatos = new ArrayList<>();
    
    @GetMapping
    public ResponseEntity<?> listAll() {
        if (contatos.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ErroDTO("Não tem nenhum cadastro no sistema", null, LocalDateTime.now()));
        }
        return ResponseEntity.ok().body(contatos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id){
        if (contatos.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ErroDTO("Não tem nenhum cadastro no sistema", null, LocalDateTime.now()));
        }
        for (Contato index : contatos) {
            if (index.getCodigo().equals(id)) {
                return ResponseEntity.ok().body(index);
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ErroDTO("Código informado não foi encontrado", id, LocalDateTime.now()));
    }

    @GetMapping("/buscar")
    public ResponseEntity<?> buscarPorNome (@RequestParam String nome){
        List<Contato> retorno = new ArrayList<>();

        for (Contato index : contatos){
            if (index.getNome().toLowerCase().contains(nome.toLowerCase())) {
                retorno.add(index);
            }
        }

        if (retorno.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ErroDTO("Nome não contém na nossa lista", null, LocalDateTime.now()));
        }

        return ResponseEntity.ok(retorno);
    }


    @PostMapping
    public ResponseEntity<?> novo (@RequestBody Contato novoContato){
        boolean existe = contatos.stream()
                         .anyMatch(contato -> contato.getCodigo().equals(novoContato.getCodigo()));
        
        if (existe){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new ErroDTO("Já existe contato de código", novoContato.getCodigo(), LocalDateTime.now()));
        }
        if (novoContato.getNome() == null || novoContato.getNome().equals("")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ErroDTO("Nome não Informado", null, LocalDateTime.now()));
        }


        contatos.add(novoContato);
        return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(novoContato);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar (@PathVariable Integer id, @RequestBody Contato contatoAtualizado){
        for(Contato index: contatos) {
            if (index.getCodigo().equals(id)) {
                index.setNome(contatoAtualizado.getNome());
                return ResponseEntity.ok(contatoAtualizado);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ErroDTO("Contato não encontrado", id, LocalDateTime.now()));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir (@PathVariable Integer id){
        boolean removido = contatos.removeIf(contato -> contato.getCodigo().equals(id));
        if (removido){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ErroDTO("Contato não encontrado", id, LocalDateTime.now()));
    }
    
}
