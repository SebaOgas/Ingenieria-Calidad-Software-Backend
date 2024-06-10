package utn.ics.services;

import java.util.Collection;
import utn.ics.entities.Variante;

public interface VarianteService extends BaseService<Variante, Long> {

  Collection<Variante> filtrarNombre(String nombre) throws Exception;

  Collection<Variante> filtrarPrincipal(String nomprod, Boolean principal) throws Exception;
}
