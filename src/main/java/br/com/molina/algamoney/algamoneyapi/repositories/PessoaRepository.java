package br.com.molina.algamoney.algamoneyapi.repositories;

import br.com.molina.algamoney.algamoneyapi.domain.models.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {


}
