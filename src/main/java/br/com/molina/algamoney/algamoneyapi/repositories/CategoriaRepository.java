package br.com.molina.algamoney.algamoneyapi.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.molina.algamoney.algamoneyapi.domain.models.CategoriaModel;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaModel, UUID>{

}
