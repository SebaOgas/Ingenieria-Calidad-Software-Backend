package utn.ics.integration;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import utn.ics.entities.Marca;
import utn.ics.services.MarcaServiceImpl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@Transactional
class MarcaServiceIntegrationTest {

    @Autowired
    private MarcaServiceImpl marcaService;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void testGetByNombre() throws Exception {
        Marca marca = Marca.builder()
                .nombre("Marolio")
                .descripcion("Le da sabor a tu vida")
                .build();

        entityManager.persist(marca);
        entityManager.flush();

        Marca marcaSaved = marcaService.getByNombre("Marolio");

        assertEquals(marcaSaved.getNombre(), marca.getNombre());
        assertEquals(marcaSaved.getDescripcion(), marca.getDescripcion());
    }

}