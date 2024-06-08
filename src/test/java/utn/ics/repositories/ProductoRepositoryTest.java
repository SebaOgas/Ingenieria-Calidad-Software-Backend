package utn.ics.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import jakarta.persistence.EntityManager;
import java.util.Calendar;
import java.util.Collection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import utn.ics.entities.Marca;
import utn.ics.entities.Producto;

@DataJpaTest
class ProductoRepositoryTest {
  @Autowired private ProductoRepository productoRepository;

  @Autowired private EntityManager entityManager;

  @Test
  void testFiltrado() {
    Marca marca = Marca.builder().nombre("ABC").descripcion("Descrip Marca").build();

    entityManager.persist(marca);
    entityManager.flush();
    marca = entityManager.find(Marca.class, 1);

    Producto producto1 =
        Producto.builder()
            .nombre("BCD")
            .descripcion("Descrip Producto")
            .visibilidad(false)
            .marca(marca)
            .build();

    entityManager.persist(producto1);
    entityManager.flush();

    Producto producto2 =
        Producto.builder()
            .nombre("CDE")
            .descripcion("Descrip Producto")
            .visibilidad(true)
            .marca(marca)
            .build();

    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.MONTH, -1);
    producto2.setFechaBaja(calendar.getTime());

    entityManager.persist(producto2);
    entityManager.flush();

    // Productos cuyo nombre o marca contienen "BC"
    Collection<Producto> productos = productoRepository.filtrar("BC", true, true, true);
    assertEquals(2, productos.size());

    // Productos cuyo nombre o marca contienen "CD"
    productos = productoRepository.filtrar("CD", true, true, true);
    assertEquals(2, productos.size());

    // Productos cuyo nombre o marca contienen "DE"
    productos = productoRepository.filtrar("DE", true, true, true);
    assertEquals(1, productos.size());
    for (Producto producto : productos) {
      assertEquals(producto.getNombre(), "CDE");
    }

    // Productos visibles
    productos = productoRepository.filtrar("BC", true, false, true);
    assertEquals(1, productos.size());
    for (Producto producto : productos) {
      assertEquals(producto.getNombre(), "CDE");
    }

    // Productos ocultos
    productos = productoRepository.filtrar("BC", false, true, true);
    assertEquals(1, productos.size());
    for (Producto producto : productos) {
      assertEquals(producto.getNombre(), "BCD");
    }

    // Productos no dados de baja
    productos = productoRepository.filtrar("BC", true, true, false);
    assertEquals(1, productos.size());
    for (Producto producto : productos) {
      assertEquals(producto.getNombre(), "BCD");
    }
  }
}
