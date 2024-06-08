package utn.ics.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import utn.ics.IcsApplication;
import utn.ics.entities.Caracteristica;
import utn.ics.entities.ValorCaracteristica;
import utn.ics.services.CaracteristicaServiceImpl;

@SpringBootTest(classes = IcsApplication.class)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CaracteristicaControllerTest {
  @Autowired private MockMvc mockMvc;

  @MockBean private CaracteristicaServiceImpl caracteristicaService;

  @Test
  void testPost() throws Exception {
    Caracteristica caracteristica = Caracteristica.builder().nombre("Color").build();

    ValorCaracteristica valor1 = ValorCaracteristica.builder().valor("Azul").build();
    caracteristica.getValores().add(valor1);
    ValorCaracteristica valor2 = ValorCaracteristica.builder().valor("Amarillo").build();
    caracteristica.getValores().add(valor2);
    ValorCaracteristica valor3 = ValorCaracteristica.builder().valor("Rojo").build();
    caracteristica.getValores().add(valor3);

    when(caracteristicaService.save(any())).thenReturn(caracteristica);

    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/caracteristica")
                .content(new ObjectMapper().writeValueAsString(caracteristica))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.nombre", is("Color")))
        .andExpect(jsonPath("$.valores[0].valor", is("Azul")))
        .andExpect(jsonPath("$.valores[1].valor", is("Amarillo")))
        .andExpect(jsonPath("$.valores[2].valor", is("Rojo")));
  }
}
