package utn.ics.repositories;

import org.springframework.stereotype.Repository;
import utn.ics.entities.Marca;

import java.util.Optional;

@Repository
public interface MarcaRepository extends BaseRepository<Marca, Long> {

    Optional<Marca> findByNombre(String nombre);
}
