package utn.ics.repositories;

import org.springframework.stereotype.Repository;
import utn.ics.entities.Caracteristica;
import utn.ics.entities.Marca;

import java.util.Optional;

@Repository
public interface CaracteristicaRepository extends BaseRepository<Caracteristica, Long> {

}
