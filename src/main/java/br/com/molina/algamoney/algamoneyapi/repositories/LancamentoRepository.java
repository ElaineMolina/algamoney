package br.com.molina.algamoney.algamoneyapi.repositories;

import br.com.molina.algamoney.algamoneyapi.domain.models.Lancamento;
import br.com.molina.algamoney.algamoneyapi.repositories.lancamento.LancamentoRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Long>,
        LancamentoRepositoryQuery {
}
