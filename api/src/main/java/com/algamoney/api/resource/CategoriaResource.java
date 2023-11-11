package com.algamoney.api.resource;

import com.algamoney.api.event.RecursoCriadoEvent;
import com.algamoney.api.model.Categoria;
import com.algamoney.api.repository.CategoriaRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("categorias")
@RequiredArgsConstructor
public class CategoriaResource {

    final CategoriaRepository categoriaRepository;
    final ApplicationEventPublisher publisher;

    @GetMapping
    public ResponseEntity<List<Categoria>> listar() {
        List<Categoria> all = categoriaRepository.findAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping("{id}")
    public ResponseEntity<Categoria> porId(@PathVariable Long id) {
        var categ = categoriaRepository.findById(id);
        return categ.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }


    @PostMapping
    public ResponseEntity<Categoria> criar(
            @Valid @RequestBody Categoria categoria, HttpServletResponse response) {
        Categoria save = categoriaRepository.save(categoria);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, save.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(save);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> apagar(@PathVariable Long id) {
        categoriaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
