package utn.ics.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import utn.ics.entities.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class VarianteRepositoryTest {

    @Autowired
    private VarianteRepository varianteRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void testFiltrarNombre(){
        Marca marca = Marca.builder().nombre("Adidas").descripcion("Es la marca Adidas").build();
        Subcategoria subcategoria = Subcategoria.builder().titulo("Camperas deportivas").build();
        List<Subcategoria> subcategorias=new ArrayList<>();
        subcategorias.add(subcategoria);
        Categoria categoria = Categoria.builder().titulo("Camperas").subcategorias(subcategorias).build();

        ValorCaracteristica valorCaracteristica = ValorCaracteristica.builder().valor("250").build();
        List<ValorCaracteristica> valoresCaracteristica = new ArrayList<>();
        valoresCaracteristica.add(valorCaracteristica);

        Caracteristica caracteristica = Caracteristica.builder().nombre("carac")
                .valores(valoresCaracteristica)
                .subcategorias(subcategorias).build();

        entityManager.persist(marca);
        entityManager.flush();
        marca = entityManager.find(Marca.class, 1);

        entityManager.persist(categoria);
        entityManager.flush();
        categoria = entityManager.find(Categoria.class, 1);

        subcategoria = categoria.getSubcategorias().iterator().next();
        entityManager.persist(subcategoria);
        entityManager.flush();
        subcategoria = entityManager.find(Subcategoria.class, 1);

        entityManager.persist(caracteristica);
        entityManager.flush();
        caracteristica = entityManager.find(Caracteristica.class, 1);

        valorCaracteristica = caracteristica.getValores().iterator().next();
        entityManager.persist(valorCaracteristica);
        entityManager.flush();
        valorCaracteristica = entityManager.find(ValorCaracteristica.class, 1);

        Producto producto = Producto.builder()
                .nombre("Campera de Boca")
                .visibilidad(true)
                .descripcion("campera de boca")
                .marca(marca)
                .subcategoria(subcategoria)
                .build();

        entityManager.persist(producto);
        entityManager.flush();
        producto = entityManager.find(Producto.class, 1);


        Variante variante1 =
                Variante.builder()
                        .nombre("Campera de Boca 2016")
                        .descripcion("campera de boca del 2016")
                        .principal(true)
                        .producto(producto)
                        .valores_caracteristica(valoresCaracteristica)
                        .build();

        entityManager.persist(variante1);
        entityManager.flush();

        Variante variante2 =
                Variante.builder()
                        .nombre("Campera de Boca 2020")
                        .descripcion("campera de boca del 2020")
                        .principal(false)
                        .producto(producto)
                        .valores_caracteristica(valoresCaracteristica)
                        .build();

        entityManager.persist(variante2);
        entityManager.flush();

        Variante variante3 =
                Variante.builder()
                        .nombre("Campera de Boca 2016 blanca")
                        .descripcion("campera de boca del 2016 blanca")
                        .principal(false)
                        .producto(producto)
                        .valores_caracteristica(valoresCaracteristica)
                        .build();

        entityManager.persist(variante3);
        entityManager.flush();

        //Variantes cuyo nombre contenga "2020"
        Collection<Variante> variantesRecuperadas = varianteRepository.filtrarNombre("2020");
        assertEquals(1, variantesRecuperadas.size());

        //Variantes cuyo nombre contenga "2016"
        variantesRecuperadas = varianteRepository.filtrarNombre("2016");
        assertEquals(2, variantesRecuperadas.size());
    }

    @Test
    void testFiltrarPrincipal(){
        Marca marca = Marca.builder().nombre("Adidas").descripcion("Es la marca Adidas").build();
        Subcategoria subcategoria = Subcategoria.builder().titulo("Camperas deportivas").build();
        List<Subcategoria> subcategorias=new ArrayList<>();
        subcategorias.add(subcategoria);
        Categoria categoria = Categoria.builder().titulo("Camperas").subcategorias(subcategorias).build();

        ValorCaracteristica valorCaracteristica = ValorCaracteristica.builder().valor("250").build();
        List<ValorCaracteristica> valoresCaracteristica = new ArrayList<>();
        valoresCaracteristica.add(valorCaracteristica);

        Caracteristica caracteristica = Caracteristica.builder().nombre("carac")
                .valores(valoresCaracteristica)
                .subcategorias(subcategorias).build();

        entityManager.persist(marca);
        entityManager.flush();
        marca = entityManager.find(Marca.class, 1);

        entityManager.persist(categoria);
        entityManager.flush();
        categoria = entityManager.find(Categoria.class, 1);

        subcategoria = categoria.getSubcategorias().iterator().next();
        entityManager.persist(subcategoria);
        entityManager.flush();
        subcategoria = entityManager.find(Subcategoria.class, 1);

        entityManager.persist(caracteristica);
        entityManager.flush();
        caracteristica = entityManager.find(Caracteristica.class, 1);

        valorCaracteristica = caracteristica.getValores().iterator().next();
        entityManager.persist(valorCaracteristica);
        entityManager.flush();
        valorCaracteristica = entityManager.find(ValorCaracteristica.class, 1);

        Producto producto = Producto.builder()
                .nombre("Campera de Boca")
                .visibilidad(true)
                .descripcion("campera de boca")
                .marca(marca)
                .subcategoria(subcategoria)
                .build();

        entityManager.persist(producto);
        entityManager.flush();
        producto = entityManager.find(Producto.class, 1);


        Variante variante1 =
                Variante.builder()
                        .nombre("Campera de Boca 2016")
                        .descripcion("campera de boca del 2016")
                        .principal(true)
                        .producto(producto)
                        .valores_caracteristica(valoresCaracteristica)
                        .build();

        entityManager.persist(variante1);
        entityManager.flush();

        Variante variante2 =
                Variante.builder()
                        .nombre("Campera de Boca 2020")
                        .descripcion("campera de boca del 2020")
                        .principal(false)
                        .producto(producto)
                        .valores_caracteristica(valoresCaracteristica)
                        .build();

        entityManager.persist(variante2);
        entityManager.flush();

        Variante variante3 =
                Variante.builder()
                        .nombre("Campera de Boca 2016 blanca")
                        .descripcion("campera de boca del 2016 blanca")
                        .principal(false)
                        .producto(producto)
                        .valores_caracteristica(valoresCaracteristica)
                        .build();

        entityManager.persist(variante3);
        entityManager.flush();

        //Variantes que sean la principal para el producto
        Collection<Variante> variantesRecuperadas = varianteRepository.filtrarPrincipal("Campera de Boca",true);
        assertEquals(3, variantesRecuperadas.size());

    }
}
