package utn.ics.services;

import utn.ics.entities.Producto;

public interface ProductoService extends BaseService<Producto, Long> {

    Producto getByNombre(String nombre) throws Exception;
}