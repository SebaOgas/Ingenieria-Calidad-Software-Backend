package utn.ics.services;

import utn.ics.entities.Variante;

import java.util.Collection;

public interface VarianteService extends BaseService<Variante, Long>{

    Collection<Variante> filtrarNombre(String nombre) throws Exception;

    Collection<Variante> filtrarPrincipal(String nomprod, Boolean principal) throws Exception;
}
