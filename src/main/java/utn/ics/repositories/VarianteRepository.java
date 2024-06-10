package utn.ics.repositories;

import java.util.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import utn.ics.entities.Variante;

@Repository
public interface VarianteRepository extends BaseRepository<Variante, Long> {

  @Query("SELECT v FROM Variante v WHERE (v.nombre LIKE %:nombre%)")
  Collection<Variante> filtrarNombre(String nombre);

  @Query(
      "SELECT v FROM Variante v WHERE (:principal = true AND principal = true)"
          + "AND (v.producto.nombre LIKE %:nomprod%)")
  Collection<Variante> filtrarPrincipal(String nomprod, Boolean principal);
}
