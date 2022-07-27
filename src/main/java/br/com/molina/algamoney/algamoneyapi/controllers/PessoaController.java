package br.com.molina.algamoney.algamoneyapi.controllers;

import br.com.molina.algamoney.algamoneyapi.domain.models.PessoaModel;
import br.com.molina.algamoney.algamoneyapi.event.RecursoCriadoEvent;
import br.com.molina.algamoney.algamoneyapi.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/pessoas")
public class PessoaController {


    @Autowired
    PessoaRepository pessoaRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public List<PessoaModel> listar() {
        return pessoaRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<PessoaModel> criar(@Valid @RequestBody PessoaModel pessoa, HttpServletResponse response) {
        PessoaModel pessoaSalva = pessoaRepository.save(pessoa);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getCodigo()));
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<PessoaModel> buscarPeloCodigo(@PathVariable Long codigo) {
        Optional<PessoaModel> pessoa = pessoaRepository.findById(codigo);
        return pessoa.isPresent() ? ResponseEntity.ok(pessoa.get()) : ResponseEntity.notFound().build();
    }
}

