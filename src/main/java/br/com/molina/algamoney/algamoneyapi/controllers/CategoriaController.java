package br.com.molina.algamoney.algamoneyapi.controllers;


import br.com.molina.algamoney.algamoneyapi.domain.models.CategoriaModel;
import br.com.molina.algamoney.algamoneyapi.dtos.CategoriaDto;
import br.com.molina.algamoney.algamoneyapi.repositories.CategoriaRepository;
import br.com.molina.algamoney.algamoneyapi.services.CategoriaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/categorias")
public class CategoriaController {

    final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @Autowired
    CategoriaRepository categoriaRepository;

    @GetMapping
    public List<CategoriaModel> listar() {
        return categoriaRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Object> saveCategoria(@RequestBody @Valid CategoriaDto categoriaDto, HttpServletResponse response) {
        var categoriaModel = new CategoriaModel();
        BeanUtils.copyProperties(categoriaDto, categoriaModel);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
                .buildAndExpand(categoriaModel.getCodigo()).toUri();
        response.setHeader("Location", uri.toASCIIString());
        return ResponseEntity.created(uri).body(categoriaService.save(categoriaModel));
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<CategoriaModel> buscarPeloCodigo(@PathVariable Long codigo) {
        Optional<CategoriaModel> categoria = categoriaRepository.findById(codigo);
        return categoria.isPresent() ? ResponseEntity.ok(categoria.get()) : ResponseEntity.notFound().build();
    }
}