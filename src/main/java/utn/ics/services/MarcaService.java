package utn.ics.services;

import utn.ics.entities.Marca;

public interface MarcaService extends BaseService<Marca,Long> {

    public Marca getByNombre(String nombre) throws Exception;
}
