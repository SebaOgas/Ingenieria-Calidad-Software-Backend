package utn.ics.repositories;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import utn.ics.entities.Categoria;
@Repository
public interface CategoriaRepository extends BaseRepository<Categoria, Long> {
  public Optional<Categoria> findByTitulo(String titulo);
}
