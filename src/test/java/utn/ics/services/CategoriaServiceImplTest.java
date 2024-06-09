package utn.ics.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import utn.ics.entities.Categoria;
import utn.ics.entities.Subcategoria;
import utn.ics.repositories.CategoriaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = utn.ics.IcsApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CategoriaServiceImplTest {
    @Autowired
    private CategoriaService categoriaService;

    @MockBean
    private CategoriaRepository categoriaRepository;

    @Test
    void testFindByTitulo() throws Exception {
        Subcategoria subcategoria1=Subcategoria.builder()
                .titulo("Deportivas")
                .build();
        Subcategoria subcategoria2=Subcategoria.builder()
                .titulo("Abrigo")
                .build();
        Subcategoria subcategoria3=Subcategoria.builder()
                .titulo("Impermeables")
                .build();
        List<Subcategoria> subcategorias=new ArrayList<>();
        subcategorias.add(subcategoria1);
        subcategorias.add(subcategoria2);
        subcategorias.add(subcategoria3);

        Categoria categoria = Categoria.builder()
                .titulo("Camperas")
                .subcategorias(subcategorias)
                .build();

        when(this.categoriaRepository.findByTitulo("Camperas")).thenReturn(Optional.of(categoria));

        Optional<Categoria> categoriaRecuperada = this.categoriaService.findByTitulo("Camperas");
        assertEquals("Camperas", categoriaRecuperada.get().getTitulo());
    }


}
