package utn.ics.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import utn.ics.entities.Paquete;
import utn.ics.repositories.PaqueteRepository;
import utn.ics.services.PaqueteServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = utn.ics.IcsApplication.class)
class PaqueteServiceImplTest {
    @Autowired
    private PaqueteServiceImpl paqueteService;

    @MockBean
    private PaqueteRepository paqueteRepository;

    @Test
    void testFindByTitulo() throws Exception {
        Paquete paquete = Paquete.builder()
                .titulo("Conjunto boquita")
                .build();

        when(this.paqueteRepository.findByTitulo("Conjunto boquita")).thenReturn(Optional.of(paquete));

        Optional<Paquete> paqueteRecuperado = this.paqueteService.findByTitulo("Conjunto boquita");
        assertEquals("Conjunto boquita", paqueteRecuperado.get().getTitulo());
    }

}
