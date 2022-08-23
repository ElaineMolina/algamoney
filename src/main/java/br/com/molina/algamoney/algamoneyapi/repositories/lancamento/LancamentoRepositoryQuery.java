package br.com.molina.algamoney.algamoneyapi.repositories.lancamento;

import br.com.molina.algamoney.algamoneyapi.domain.models.Lancamento;
import br.com.molina.algamoney.algamoneyapi.repositories.filter.LancamentoFilter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LancamentoRepositoryQuery  {

    public List< Lancamento> filtrar(LancamentoFilter lancamentoFilter);
}
