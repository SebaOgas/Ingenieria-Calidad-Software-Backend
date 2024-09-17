package utn.ics.integration;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import utn.ics.entities.Categoria;
import utn.ics.entities.Marca;
import utn.ics.entities.Producto;
import utn.ics.entities.Subcategoria;
import utn.ics.services.ProductoServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class ProductoServiceIntegrationTest {
    @Autowired
    private ProductoServiceImpl productoService;

    @Autowired private EntityManager entityManager;

    @Test
    public void testGetByNombre() throws Exception {
        Marca marca = Marca.builder().nombre("Marca").descripcion("Descripcion Marca").build();
        Subcategoria subcategoria = Subcategoria.builder().titulo("Subcategoriaaaaaaa").build();
        List<Subcategoria> subcategorias = new ArrayList<>();
        subcategorias.add(subcategoria);
        Categoria categoria = Categoria.builder()
                        .titulo("Categoria")
                        .subcategorias(subcategorias)
                        .build();

        entityManager.persist(marca);
        entityManager.flush();
        marca = entityManager.find(Marca.class, 1);

        entityManager.persist(categoria);
        entityManager.flush();
        categoria = entityManager.find(Categoria.class, 1);

        subcategoria = categoria.getSubcategorias().iterator().next();
        entityManager.persist(subcategoria);
        entityManager.flush();
        subcategoria = entityManager.find(Subcategoria.class, 1);

        Producto producto =
                Producto.builder()
                        .nombre("El mejor producto de la historia")
                        .descripcion("Literalmente el mejor producto de la historia")
                        .visibilidad(false)
                        .marca(marca)
                        .subcategoria(subcategoria)
                        .build();

        entityManager.persist(producto);
        entityManager.flush();

        Producto productoSaved = productoService.getByNombre("El mejor producto de la historia");

        assertEquals(productoSaved.getNombre(), producto.getNombre());
        assertEquals(productoSaved.getDescripcion(), producto.getDescripcion());
        assertEquals(productoSaved.getMarca().getNombre(), producto.getMarca().getNombre());
        assertEquals(productoSaved.getSubcategoria().getTitulo(), producto.getSubcategoria().getTitulo());
    }
}
