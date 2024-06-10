package utn.ics.services;

import java.util.Optional;
import utn.ics.entities.Paquete;

public interface PaqueteService extends BaseService<Paquete, Long> {

  public Optional<Paquete> findByTitulo(String titulo);
}
