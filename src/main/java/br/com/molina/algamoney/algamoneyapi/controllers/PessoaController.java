package br.com.molina.algamoney.algamoneyapi.controllers;

import br.com.molina.algamoney.algamoneyapi.domain.models.PessoaModel;
import br.com.molina.algamoney.algamoneyapi.event.RecursoCriadoEvent;
import br.com.molina.algamoney.algamoneyapi.repositories.PessoaRepository;
import br.com.molina.algamoney.algamoneyapi.service.PessoaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
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
@RequestMapping("/pessoas")
public class PessoaController {

    private PessoaRepository pessoaRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private PessoaService pessoaService;

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
    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long codigo){
        pessoaRepository.deleteById(codigo);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<PessoaModel> atualizar(@PathVariable Long codigo,
                                                 @Valid @RequestBody PessoaModel pessoa){
        PessoaModel pessoaSalva = pessoaService.atualizar(codigo,pessoa);
        return ResponseEntity.ok(pessoaSalva);
    }
}

