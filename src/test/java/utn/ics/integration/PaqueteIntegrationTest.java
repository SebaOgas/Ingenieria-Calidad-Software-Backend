package utn.ics.integration;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import utn.ics.IcsApplication;
import utn.ics.entities.*;
import utn.ics.repositories.PaqueteRepository;
import utn.ics.services.PaqueteServiceImpl;

@SpringBootTest(classes = IcsApplication.class)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Transactional
public class PaqueteIntegrationTest {

  @Autowired private MockMvc mockMvc;

  @Autowired private PaqueteServiceImpl paqueteService;

  @Autowired private PaqueteRepository paqueteRepository;

  @Autowired private EntityManager entityManager;

  @Test
  void testFindPaqueteByTitulo() throws Exception {

    Subcategoria subcategoria1 = Subcategoria.builder().titulo("Camperas deportivas").build();

    Subcategoria subcategoria2 = Subcategoria.builder().titulo("Zapatillas deportivas").build();

    List<Subcategoria> subcategorias = new ArrayList<>();
    subcategorias.add(subcategoria1);
    subcategorias.add(subcategoria2);

    Categoria categoria =
        Categoria.builder().titulo("Equipamiento Deportivo").subcategorias(subcategorias).build();

    entityManager.persist(categoria);
    entityManager.flush();

    Marca marca1 =
        Marca.builder().nombre("Nike").descripcion("Indumentaria deportiva Nike").build();

    entityManager.persist(marca1);
    entityManager.flush();

    Producto producto1 =
        Producto.builder()
            .nombre("Campera Nike")
            .descripcion("Campera deportiva Nike modelo 2017")
            .marca(marca1)
            .visibilidad(true)
            .subcategoria(subcategoria1)
            .build();

    Producto producto2 =
        Producto.builder()
            .nombre("Nike Air Zooms")
            .descripcion("Modelo ligero y reforzado de zapatillas para correr")
            .marca(marca1)
            .visibilidad(true)
            .subcategoria(subcategoria2)
            .build();

    entityManager.persist(producto1);
    entityManager.persist(producto2);
    entityManager.flush();

    List<Producto> productos = new ArrayList<>();
    productos.add(producto1);
    productos.add(producto2);

    Paquete paquete = Paquete.builder().titulo("Paquete Deporte").listaProductos(productos).build();

    entityManager.persist(paquete);
    entityManager.flush();

    try {
      this.mockMvc
          .perform(get("/paquetes/nombre=Paquete Deporte").contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.titulo", is("Paquete Deporte")))
          .andExpect(jsonPath("$.listaProductos[0].nombre", is("Campera Nike")))
          .andExpect(jsonPath("$.listaProductos[1].nombre", is("Nike Air Zooms")));
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
  }
}
