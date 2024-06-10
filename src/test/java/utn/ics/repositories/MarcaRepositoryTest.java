package utn.ics.repositories;

import static org.junit.jupiter.api.Assertions.*;

import jakarta.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import utn.ics.entities.*;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class MarcaRepositoryTest {
  @Autowired private MarcaRepository marcaRepository;

  @Autowired private EntityManager entityManager;

  @Test
  void testFindMarcaByNombreProducto() {

    Marca marca = Marca.builder().nombre("Nike").build();
    // Creo las subcategoría porque es No pueden ser nulas ;D
    Subcategoria subcategoria = Subcategoria.builder().titulo("Sub").build();
    List<Subcategoria> subcategorias = new ArrayList<>();
    subcategorias.add(subcategoria);
    Categoria categoria =
        Categoria.builder().titulo("Categoria").subcategorias(subcategorias).build();

    Producto producto1 =
        Producto.builder()
            .nombre("Campera boquita")
            .marca(marca)
            .subcategoria(subcategoria)
            .visibilidad(true)
            .build();

    entityManager.persist(marca);
    entityManager.persist(categoria);
    entityManager.persist(subcategoria);
    entityManager.persist(producto1);
    entityManager.flush();

    // Llamar al método del repositorio para encontrar la marca por nombre de producto
    Optional<Marca> marcaOptional =
        marcaRepository.findMarcaByNombreProducto(producto1.getNombre());

    // Verificar que se haya encontrado la marca
    assertTrue(marcaOptional.isPresent());
    assertEquals(marca, marcaOptional.get());
  }
}
