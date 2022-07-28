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
        Optional<PessoaModel> pessoaSalva = pessoaRepository.findById(codigo);
        if(pessoaSalva == null){
            throw new EmptyResultDataAccessException(1);
        }
        BeanUtils.copyProperties(pessoa, pessoaSalva,"codigo");
        return pessoaRepository.save(pessoa);
    }

}
