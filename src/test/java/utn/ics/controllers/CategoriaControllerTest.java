package utn.ics.controllers;

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
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import utn.ics.IcsApplication;
import utn.ics.entities.Categoria;
import utn.ics.entities.Subcategoria;
import utn.ics.services.CategoriaServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = IcsApplication.class)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CategoriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoriaServiceImpl categoriaService;

    @Test
    void getAllCategorias() throws Exception{
        Subcategoria subcategoria1=Subcategoria.builder()
                .titulo("Deportivas")
                .build();
        Subcategoria subcategoria2=Subcategoria.builder()
                .titulo("Abrigo")
                .build();
        Subcategoria subcategoria3=Subcategoria.builder()
                .titulo("Impermeables")
                .build();
        List<Subcategoria> subcategorias1=new ArrayList<>();
        subcategorias1.add(subcategoria1);
        subcategorias1.add(subcategoria2);
        subcategorias1.add(subcategoria3);

        Categoria categoria1 = Categoria.builder()
                .titulo("Camperas")
                .subcategorias(subcategorias1)
                .build();

        Subcategoria subcategoria4=Subcategoria.builder()
                .titulo("Deportivos")
                .build();
        Subcategoria subcategoria5=Subcategoria.builder()
                .titulo("Jeans")
                .build();
        List<Subcategoria> subcategorias2=new ArrayList<>();
        subcategorias2.add(subcategoria4);
        subcategorias2.add(subcategoria5);

        Categoria categoria2 = Categoria.builder()
                .titulo("Pantalones")
                .subcategorias(subcategorias2)
                .build();
        List<Categoria> categorias=new ArrayList<>();
        categorias.add(categoria1);
        categorias.add(categoria2);

        when(categoriaService.findAll()).thenReturn(categorias);

        mockMvc
            .perform(
                MockMvcRequestBuilders.get("/categoria")
                    .content(new ObjectMapper().writeValueAsString(categorias))
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].titulo", is("Camperas")))
            .andExpect(jsonPath("$[0].subcategorias[0].titulo", is("Deportivas")))
            .andExpect(jsonPath("$[0].subcategorias[1].titulo", is("Abrigo")))
            .andExpect(jsonPath("$[0].subcategorias[2].titulo", is("Impermeables")))
            .andExpect(jsonPath("$[1].titulo", is("Pantalones")))
            .andExpect(jsonPath("$[1].subcategorias[0].titulo", is("Deportivos")))
            .andExpect(jsonPath("$[1].subcategorias[1].titulo", is("Jeans")));
    }
}
