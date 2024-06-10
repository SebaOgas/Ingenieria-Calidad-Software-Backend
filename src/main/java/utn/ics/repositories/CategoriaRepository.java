package utn.ics.repositories;

import java.util.Optional;
import utn.ics.entities.Categoria;

public interface CategoriaRepository extends BaseRepository<Categoria, Long> {
  public Optional<Categoria> findByTitulo(String titulo);
}
