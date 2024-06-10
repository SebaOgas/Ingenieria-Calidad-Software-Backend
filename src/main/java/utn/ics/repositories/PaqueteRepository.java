package utn.ics.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utn.ics.entities.Paquete;

@Repository
public interface PaqueteRepository extends BaseRepository<Paquete, Long> {

  public Optional<Paquete> findByTitulo(String titulo);

  @Query(
      value =
          "SELECT paq.* FROM paquetes as paq "
              + "INNER JOIN paquetes_productos as paq_prod ON paq.numero = paq_prod.numero_paquete "
              + "INNER JOIN producto as prod ON paq_prod.numero_producto = prod.numero "
              + "WHERE prod.nombre LIKE %:filtro1% "
              + "ORDER BY prod.nombre",
      nativeQuery = true)
  List<Paquete> obtenerPaqueteAsociado(@Param("filtro1") String filtro1);
}
