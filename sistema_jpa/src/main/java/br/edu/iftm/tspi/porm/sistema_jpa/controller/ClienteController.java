package br.edu.iftm.tspi.porm.sistema_jpa.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.iftm.tspi.porm.sistema_jpa.domain.Cliente;
import br.edu.iftm.tspi.porm.sistema_jpa.dto.ClienteDto;
import br.edu.iftm.tspi.porm.sistema_jpa.mapper.ClienteMapper;
import br.edu.iftm.tspi.porm.sistema_jpa.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteRepository repository;
    private final ClienteMapper mapper;

    public ClienteController(ClienteRepository repository, ClienteMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<ClienteDto>> listAll() {
        List<Cliente> clientes = repository.findAll();
        if (clientes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(mapper.toDtoList(clientes));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> listPorId(@PathVariable String id) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado para o ID: " + id));
        return ResponseEntity.ok(mapper.toDto(cliente));
    }

    @PostMapping
    public ResponseEntity<ClienteDto> newCliente(@Valid @RequestBody ClienteDto clienteDto) {
        Cliente clienteSalvo = repository.save(mapper.toEntity(clienteDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(clienteSalvo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> update(@PathVariable String id, @Valid @RequestBody ClienteDto clienteDto) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Cliente com ID " + id + " não encontrado");
        }
        clienteDto.setId(id);
        Cliente atualizado = repository.save(mapper.toEntity(clienteDto));
        return ResponseEntity.ok(mapper.toDto(atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente com ID " + id + " não encontrado"));
        repository.delete(cliente);
        return ResponseEntity.noContent().build();
    }
}
