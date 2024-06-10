package utn.ics.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import jakarta.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import utn.ics.entities.Categoria;
import utn.ics.entities.Subcategoria;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CategoriaRepositoryTest {

  @Autowired private CategoriaRepository categoriaRepository;
  @Autowired private EntityManager entityManager;

  @Test
  void testFindByName() {
    Subcategoria subcategoria1 = Subcategoria.builder().titulo("Deportivas").build();
    Subcategoria subcategoria2 = Subcategoria.builder().titulo("Abrigo").build();
    Subcategoria subcategoria3 = Subcategoria.builder().titulo("Impermeables").build();
    List<Subcategoria> subcategorias = new ArrayList<>();
    subcategorias.add(subcategoria1);
    subcategorias.add(subcategoria2);
    subcategorias.add(subcategoria3);

    Categoria categoria =
        Categoria.builder().titulo("Camperas").subcategorias(subcategorias).build();

    entityManager.persist(categoria);
    entityManager.flush();

    Optional<Categoria> categoriaRecuperada = this.categoriaRepository.findByTitulo("Camperas");

    assertEquals(categoria, categoriaRecuperada.get());
  }
}
