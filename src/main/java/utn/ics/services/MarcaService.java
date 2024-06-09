package utn.ics.services;

import utn.ics.entities.Marca;

import java.util.List;

public interface MarcaService extends BaseService<Marca, Long> {

  Marca getByNombre(String nombre) throws Exception;
  Marca findMarcaByNombreProducto(String productoNombre) throws Exception;
}
