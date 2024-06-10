package utn.ics.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import utn.ics.IcsApplication;
import utn.ics.entities.Marca;
import utn.ics.services.MarcaServiceImpl;

@SpringBootTest(classes = IcsApplication.class)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class MarcaControllerTest {
  @Autowired private MockMvc mockMvc;

  @MockBean private MarcaServiceImpl marcaService;
  @InjectMocks private MarcaController marcaController;

  @Test
  void testCreateMarca() throws Exception {
    Marca marca = Marca.builder().nombre("Nike").descripcion("Descripción de nike?????").build();

    when(marcaService.save(any(Marca.class))).thenReturn(marca);

    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/marca")
                .content(new ObjectMapper().writeValueAsString(marca))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.nombre", is("Nike")))
        .andExpect(jsonPath("$.descripcion", is("Descripción de nike?????")));
  }

  @Test
  void testGetAllMarcas() throws Exception {
    Marca marca1 = Marca.builder().nombre("Marca 1").build();
    Marca marca2 = Marca.builder().nombre("Marca 2").build();
    List<Marca> marcas = List.of(marca1, marca2);
    when(marcaService.findAll()).thenReturn(marcas);
    mockMvc
        .perform(MockMvcRequestBuilders.get("/marca"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[0].nombre", is("Marca 1")))
        .andExpect(jsonPath("$[1].nombre", is("Marca 2")));
  }
}
