package utn.ics.repositories;

import java.util.Optional;
import org.springframework.stereotype.Repository;
import utn.ics.entities.Marca;

@Repository
public interface MarcaRepository extends BaseRepository<Marca, Long> {

  Optional<Marca> findByNombre(String nombre);
}
