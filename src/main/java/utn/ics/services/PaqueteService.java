package utn.ics.services;

import utn.ics.entities.Paquete;

import java.util.List;
import java.util.Optional;

public interface PaqueteService extends BaseService<Paquete, Long> {

    public Optional<Paquete> findByTitulo(String titulo);
}
