package br.com.molina.algamoney.algamoneyapi.services;

import br.com.molina.algamoney.algamoneyapi.domain.models.CategoriaModel;
import org.springframework.stereotype.Service;

import br.com.molina.algamoney.algamoneyapi.repositories.CategoriaRepository;

import javax.transaction.Transactional;

@Service
public class CategoriaService {

    final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional
    public CategoriaModel save(CategoriaModel categoriaModel) {
        return categoriaRepository.save(categoriaModel);
    }
}
