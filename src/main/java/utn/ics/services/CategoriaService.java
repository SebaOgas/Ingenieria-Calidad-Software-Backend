package utn.ics.services;

import java.util.Optional;
import utn.ics.entities.Categoria;

public interface CategoriaService extends BaseService<Categoria, Long> {
  public Optional<Categoria> findByTitulo(String titulo);
}
