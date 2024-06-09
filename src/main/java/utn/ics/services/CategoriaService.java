package utn.ics.services;

import utn.ics.entities.Categoria;

import java.util.Optional;

public interface CategoriaService extends BaseService<Categoria, Long> {
    public Optional<Categoria> findByTitulo(String titulo);
}
