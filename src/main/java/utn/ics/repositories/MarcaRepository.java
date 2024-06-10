package utn.ics.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import utn.ics.entities.Marca;

@Repository
public interface MarcaRepository extends BaseRepository<Marca, Long> {

  Optional<Marca> findByNombre(String nombre);

  @Query("SELECT p.marca FROM Producto p WHERE p.nombre = :productoNombre")
  Optional<Marca> findMarcaByNombreProducto(String productoNombre);
}
