package br.com.molina.algamoney.algamoneyapi.controllers;


import br.com.molina.algamoney.algamoneyapi.domain.models.CategoriaModel;
import br.com.molina.algamoney.algamoneyapi.dtos.CategoriaDto;
import br.com.molina.algamoney.algamoneyapi.repositories.CategoriaRepository;
import br.com.molina.algamoney.algamoneyapi.services.CategoriaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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


//    @GetMapping("/{codigo}")
//    public Optional<CategoriaModel> buscarPeloCodigo(@PathVariable UUID codigo){
//        return null;
//    }

    @GetMapping
    public List<CategoriaModel> listar(){
        return categoriaRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Object> saveCategoria(@RequestBody @Valid CategoriaDto categoriaDto){
        var categoriaModel = new CategoriaModel();
        BeanUtils.copyProperties(categoriaDto, categoriaModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.save(categoriaModel));
    }
}
