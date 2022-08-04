package br.com.molina.algamoney.algamoneyapi.service;


import br.com.molina.algamoney.algamoneyapi.domain.models.PessoaModel;
import br.com.molina.algamoney.algamoneyapi.repositories.PessoaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public PessoaModel atualizar(Long codigo, PessoaModel pessoa){
        PessoaModel pessoaSalva = buscarPessoaPeloCodigo(codigo);

        BeanUtils.copyProperties(pessoa, pessoaSalva,"codigo");
        return pessoaRepository.save(pessoaSalva);
    }

    public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
        PessoaModel pessoaSalva = buscarPessoaPeloCodigo(codigo);
        pessoaSalva.setAtivo(ativo);
        pessoaRepository.save(pessoaSalva);

    }
    private PessoaModel buscarPessoaPeloCodigo(Long codigo) {
        PessoaModel pessoaSalva = pessoaRepository.findById(codigo)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));

        return pessoaSalva;
    }

}
