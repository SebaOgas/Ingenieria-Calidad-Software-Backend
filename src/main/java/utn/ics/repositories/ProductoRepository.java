package utn.ics.repositories;

import java.util.Collection;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import utn.ics.entities.Producto;

@Repository
public interface ProductoRepository extends BaseRepository<Producto, Long> {

  Optional<Producto> findByNombre(String nombre);

  @Query(
      "SELECT p FROM Producto p WHERE (p.nombre LIKE %:nombre% OR p.marca.nombre LIKE %:nombre%)"
          + " AND (:visibles = false AND visibilidad = false OR :visibles = true)AND (:ocultos ="
          + " false AND visibilidad = true OR :ocultos = true)AND (:baja = false AND (fechaBaja IS"
          + " NULL OR fechaBaja >= CURRENT_TIMESTAMP) OR :baja = true)")
  Collection<Producto> filtrar(String nombre, Boolean visibles, Boolean ocultos, Boolean baja);
}
