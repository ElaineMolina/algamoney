package br.com.molina.algamoney.algamoneyapi.repositories.lancamento;

import br.com.molina.algamoney.algamoneyapi.domain.models.Lancamento;
import br.com.molina.algamoney.algamoneyapi.repositories.filter.LancamentoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LancamentoRepositoryQuery  {

    public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);
}
