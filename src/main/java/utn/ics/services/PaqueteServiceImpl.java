package utn.ics.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.ics.entities.Paquete;
import utn.ics.repositories.BaseRepository;
import utn.ics.repositories.PaqueteRepository;

@Service
public class PaqueteServiceImpl extends BaseServiceImpl<Paquete, Long> implements PaqueteService {
  @Autowired private final PaqueteRepository paqueteRepository;

  public PaqueteServiceImpl(
      BaseRepository<Paquete, Long> baseRepository, PaqueteRepository paqueteRepository) {
    super(baseRepository);
    this.paqueteRepository = paqueteRepository;
  }

  public Optional<Paquete> findByTitulo(String titulo) {
    return this.paqueteRepository.findByTitulo(titulo);
  }
}
