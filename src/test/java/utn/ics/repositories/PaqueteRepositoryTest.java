package utn.ics.repositories;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import utn.ics.entities.Marca;
import utn.ics.entities.Paquete;
import utn.ics.entities.Producto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class PaqueteRepositoryTest {

    @Autowired
    private PaqueteRepository paqueteRepository;
    @Autowired
    private EntityManager entityManager;

    @Test
    void testFindByName(){
        Paquete paquete = Paquete.builder()
                            .titulo("Conjunto boquita")
                            .build();

        entityManager.persist(paquete);
        entityManager.flush();

        Optional<Paquete> paqueteRecuperado = this.paqueteRepository.findByTitulo("Conjunto boquita");

        assertEquals(paquete, paqueteRecuperado.get());
    }

    @Test
    void testObtenerPaqueteAsociado(){
        Marca marca = Marca.builder()
                .nombre("Adidas").build();

        Producto producto1 = Producto.builder()
                .nombre("Campera boquita")
                .marca(marca)
                .visibilidad(true)
                .build();

        Producto producto2 = Producto.builder()
                .nombre("Campera river")
                .marca(marca)
                .visibilidad(true)
                .build();

        Paquete paquete1 = Paquete.builder()
                .titulo("Conjunto boquita")
                .build();

        Paquete paquete2 = Paquete.builder()
                .titulo("Conjunto river")
                .build();

        paquete1.add(producto1);
        paquete2.add(producto2);

        entityManager.persist(marca);
        entityManager.persist(producto1);
        entityManager.persist(producto2);
        entityManager.persist(paquete1);
        entityManager.persist(paquete2);
        entityManager.flush();

        List<Paquete> paqueteRecuperado = this.paqueteRepository.obtenerPaqueteAsociado("boquita");

        System.out.println(paqueteRecuperado.toString());

        assertEquals(paquete1, paqueteRecuperado.get(0));
    }
}
