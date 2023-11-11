package com.algamoney.api.resource;

import com.algamoney.api.event.RecursoCriadoEvent;
import com.algamoney.api.model.Categoria;
import com.algamoney.api.model.Pessoa;
import com.algamoney.api.repository.CategoriaRepository;
import com.algamoney.api.repository.PessoaRepository;
import com.algamoney.api.service.PessoaService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("pessoas")
@RequiredArgsConstructor
public class PessoaResource {


    final PessoaRepository pessoaRepository;
    final PessoaService pessoaService;
    final ApplicationEventPublisher publisher;

    @GetMapping
    public ResponseEntity<List<Pessoa>> listar() {
        List<Pessoa> all = pessoaRepository.findAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping("{id}")
    public ResponseEntity<Pessoa> porId(@PathVariable Long id) {
        var p = pessoaRepository.findById(id);
        return p.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }


    @PostMapping
    public ResponseEntity<Pessoa> criar(
            @Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {
        Pessoa save = pessoaRepository.save(pessoa);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, save.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(save);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> apagar(@PathVariable Long id) {
        pessoaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Pessoa> atualizar(@PathVariable Long id, @Valid @RequestBody Pessoa pessoa,
                                            HttpServletResponse response) {
        Pessoa saved = pessoaService.atualizarPessoa(id, pessoa);
        return ResponseEntity.ok(saved);

    }

    @PutMapping("{id}/ativo")
    public ResponseEntity<Pessoa> atualizar(@PathVariable Long id,
                                            @RequestBody Boolean ativo) {
       pessoaService.atualizarAtivoPessoa(id, ativo);
        return ResponseEntity.noContent().build();

    }


}