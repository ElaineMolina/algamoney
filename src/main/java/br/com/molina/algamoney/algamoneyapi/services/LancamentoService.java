package br.com.molina.algamoney.algamoneyapi.services;

import br.com.molina.algamoney.algamoneyapi.domain.models.Lancamento;
import br.com.molina.algamoney.algamoneyapi.domain.models.Pessoa;
import br.com.molina.algamoney.algamoneyapi.repositories.LancamentoRepository;
import br.com.molina.algamoney.algamoneyapi.repositories.PessoaRepository;
import br.com.molina.algamoney.algamoneyapi.services.exception.PessoaInexistenteOuInativaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LancamentoService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private LancamentoRepository lancamentoRepository;

    public Lancamento salvar(Lancamento lancamento){

        Optional<Pessoa> pessoa = pessoaRepository.findById(lancamento.getPessoa().getCodigo());
        if(pessoa.isEmpty() || pessoa.get().isInativo()){
            throw new PessoaInexistenteOuInativaException();
        }
        return lancamentoRepository.save(lancamento);
    }
    
}
