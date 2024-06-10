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
import utn.ics.entities.Paquete;
import utn.ics.services.PaqueteServiceImpl;

import java.util.Arrays;

@SpringBootTest(classes = IcsApplication.class)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class PaqueteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaqueteServiceImpl paqueteService;

    @Test
    void testGetAllPaquetes() throws Exception {
        Paquete paquete1 = Paquete.builder().titulo("Paquete 1").build();
        Paquete paquete2 = Paquete.builder().titulo("Paquete 2").build();

        when(paqueteService.findAll()).thenReturn(Arrays.asList(paquete1, paquete2));

        mockMvc.perform(MockMvcRequestBuilders.get("/paquetes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].titulo", is("Paquete 1")))
                .andExpect(jsonPath("$[1].titulo", is("Paquete 2")));
    }

    @Test
    void testGetPaqueteById() throws Exception {
        Paquete paquete = Paquete.builder().titulo("Paquete 1").build();

        when(paqueteService.findById(1L)).thenReturn(paquete);

        mockMvc.perform(MockMvcRequestBuilders.get("/paquetes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo", is("Paquete 1")));
    }

    @Test
    void testSavePaquete() throws Exception {
        Paquete paquete = Paquete.builder().titulo("Paquete 1").build();

        when(paqueteService.save(any(Paquete.class))).thenReturn(paquete);

        mockMvc.perform(MockMvcRequestBuilders.post("/paquetes")
                        .content(new ObjectMapper().writeValueAsString(paquete))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo", is("Paquete 1")));
    }
}
