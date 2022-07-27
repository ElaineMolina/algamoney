package br.com.molina.algamoney.algamoneyapi.controllers;

import br.com.molina.algamoney.algamoneyapi.domain.models.CategoriaModel;
import br.com.molina.algamoney.algamoneyapi.domain.models.PessoaModel;
import br.com.molina.algamoney.algamoneyapi.repositories.PessoaRepository;
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
@RequestMapping("/pessoas")
public class PessoaController {


    @Autowired
    PessoaRepository pessoaRepository;

    @GetMapping
    public List<PessoaModel> listar() {
        return pessoaRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<PessoaModel> criar(@Valid @RequestBody PessoaModel pessoa, HttpServletResponse response) {
        PessoaModel pessoaSalva = pessoaRepository.save(pessoa);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
                .buildAndExpand(pessoaSalva.getCodigo()).toUri();
        response.setHeader("Location", uri.toASCIIString());
        return ResponseEntity.created(uri).body(pessoaSalva);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<PessoaModel> buscarPeloCodigo(@PathVariable Long codigo) {
        Optional<PessoaModel> pessoa = pessoaRepository.findById(codigo);
        return pessoa.isPresent() ? ResponseEntity.ok(pessoa.get()) : ResponseEntity.notFound().build();
    }
}

