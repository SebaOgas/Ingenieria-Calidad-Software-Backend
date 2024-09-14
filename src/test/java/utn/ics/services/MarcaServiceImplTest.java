package utn.ics.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import utn.ics.entities.Marca;
import utn.ics.repositories.MarcaRepository;

@SpringBootTest(classes = utn.ics.IcsApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class MarcaServiceImplTest {
  @Autowired private MarcaService marcaService;

  @MockBean private MarcaRepository marcaRepository;

  @Test
  void testMarcas() throws Exception {

    String nombre = "Marca A";
    String descripcion = "Descripción";

    Marca marca = Marca.builder().nombre(nombre).descripcion(descripcion).build();

    Optional<Marca> optionalMarca = Optional.of(marca);

    when(marcaRepository.findByNombre(nombre)).thenReturn(optionalMarca);

    Marca m = marcaService.getByNombre(nombre);
    assertEquals(nombre, m.getNombre());
    assertEquals(descripcion, m.getDescripcion());
  }
}
