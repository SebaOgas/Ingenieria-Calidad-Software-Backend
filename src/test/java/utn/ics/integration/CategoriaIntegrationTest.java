package utn.ics.integration;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Collection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import utn.ics.IcsApplication;
import utn.ics.entities.Categoria;
import utn.ics.entities.Subcategoria;
import utn.ics.repositories.CategoriaRepository;

@SpringBootTest(classes = IcsApplication.class)
@AutoConfigureMockMvc(addFilters = false)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CategoriaIntegrationTest {

  @Autowired private MockMvc mockMvc;

  @Autowired private CategoriaRepository categoriaRepository;

  @Test
  void getAllCategorias() throws Exception {

    Categoria categoria1 = Categoria.builder().titulo("Camperas").build();
    categoriaRepository.save(categoria1);

    Categoria categoria2 = Categoria.builder().titulo("Pantalones").build();
    categoriaRepository.save(categoria2);

    mockMvc
        .perform(MockMvcRequestBuilders.get("/categoria").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[0].titulo", is("Camperas")))
        .andExpect(jsonPath("$[1].titulo", is("Pantalones")));
  }

  @Test
  void testCreateCategoria() throws Exception {
    Subcategoria subcategoria = Subcategoria.builder().titulo("Zapatillas relocas").build();

    Collection<Subcategoria> subcategorias = new ArrayList<>();
    subcategorias.add(subcategoria);

    Categoria categoria =
        Categoria.builder().titulo("Zapatillas").subcategorias(subcategorias).build();

    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/categoria")
                .content(new ObjectMapper().writeValueAsString(categoria))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.titulo", is("Zapatillas")))
        .andExpect(jsonPath("$.subcategorias[0].titulo", is("Zapatillas relocas")));
  }
}
