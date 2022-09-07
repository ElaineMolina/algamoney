package br.com.molina.algamoney.algamoneyapi.controllers;


import br.com.molina.algamoney.algamoneyapi.domain.models.Categoria;
import br.com.molina.algamoney.algamoneyapi.events.RecursoCriadoEvent;
import br.com.molina.algamoney.algamoneyapi.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ApplicationEventPublisher publisher;
    @GetMapping
    public List<Categoria> listar() {
        return categoriaRepository.findAll();
    }
    @PostMapping
    public ResponseEntity<Categoria> criar(@Valid @RequestBody Categoria categoria, HttpServletResponse response) {
        Categoria categoriaSalva = categoriaRepository.save(categoria);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, categoriaSalva.getCodigo()));
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
    }
    @GetMapping("/{codigo}")
    public ResponseEntity<Categoria> buscarPeloCodigo(@PathVariable Long codigo) {
        Optional<Categoria> categoria = categoriaRepository.findById(codigo);
        return categoria.isPresent() ? ResponseEntity.ok(categoria.get()) : ResponseEntity.notFound().build();
    }
    @PutMapping("/{codigo}")
    public ResponseEntity<Categoria> atualizar(@PathVariable Long codigo,
                                               @Valid @RequestBody Categoria categoria){
        if(!categoriaRepository.existsById(codigo)){
            throw new EmptyResultDataAccessException(1);
        }
        categoria.setCodigo(codigo);
        categoria = categoriaRepository.save(categoria);
        return ResponseEntity.ok(categoria);
    }
}