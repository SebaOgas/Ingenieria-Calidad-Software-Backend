package utn.ics.repositories;

import utn.ics.entities.Categoria;

import java.util.Optional;

public interface CategoriaRepository extends BaseRepository<Categoria, Long> {
    public Optional<Categoria> findByTitulo(String titulo);
}
