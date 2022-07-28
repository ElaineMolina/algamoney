package br.com.molina.algamoney.algamoneyapi.controllers;


import br.com.molina.algamoney.algamoneyapi.domain.models.CategoriaModel;
import br.com.molina.algamoney.algamoneyapi.event.RecursoCriadoEvent;
import br.com.molina.algamoney.algamoneyapi.repositories.CategoriaRepository;
import lombok.AllArgsConstructor;
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

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/categorias")
public class CategoriaController {
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ApplicationEventPublisher publisher;
    @GetMapping
    public List<CategoriaModel> listar() {
        return categoriaRepository.findAll();
    }
    @PostMapping
    public ResponseEntity<CategoriaModel> criar(@Valid @RequestBody CategoriaModel categoria, HttpServletResponse response) {
        CategoriaModel categoriaSalva = categoriaRepository.save(categoria);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, categoriaSalva.getCodigo()));
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<CategoriaModel> buscarPeloCodigo(@PathVariable Long codigo) {
        Optional<CategoriaModel> categoria = categoriaRepository.findById(codigo);
        return categoria.isPresent() ? ResponseEntity.ok(categoria.get()) : ResponseEntity.notFound().build();
    }
    @PutMapping("/{codigo}")
    public ResponseEntity<CategoriaModel> atualizar(@PathVariable Long codigo,
                                           @Valid @RequestBody CategoriaModel categoria){
        if(!categoriaRepository.existsById(codigo)){
            throw new EmptyResultDataAccessException(1);
        }
        categoria.setCodigo(codigo);
        categoria = categoriaRepository.save(categoria);
        return ResponseEntity.ok(categoria);
    }
}