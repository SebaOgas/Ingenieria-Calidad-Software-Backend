package utn.ics.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.ics.entities.Producto;
import utn.ics.repositories.BaseRepository;
import utn.ics.repositories.ProductoRepository;

@Service
public class ProductoServiceImpl extends BaseServiceImpl<Producto, Long>
    implements ProductoService {

  @Autowired private final ProductoRepository productoRepository;

  public ProductoServiceImpl(
      BaseRepository<Producto, Long> baseRepository, ProductoRepository productoRepository) {
    super(baseRepository);
    this.productoRepository = productoRepository;
  }

  @Override
  public Producto getByNombre(String nombre) throws Exception {
    Optional<Producto> optionalProducto = productoRepository.findByNombre(nombre);
    if (optionalProducto.isEmpty()) throw new Exception("No se encontró el producto");
    return optionalProducto.get();
  }
}
