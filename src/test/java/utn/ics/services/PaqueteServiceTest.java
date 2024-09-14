package utn.ics.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import utn.ics.entities.Paquete;
import utn.ics.repositories.PaqueteRepository;

@SpringBootTest(classes = utn.ics.IcsApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class PaqueteServiceImplTest {
  @Autowired private PaqueteServiceImpl paqueteService;

  @MockBean private PaqueteRepository paqueteRepository;

  @Test
  void testFindByTitulo() throws Exception {
    Paquete paquete = Paquete.builder().titulo("Conjunto boquita").build();

    when(this.paqueteRepository.findByTitulo("Conjunto boquita")).thenReturn(Optional.of(paquete));

    Optional<Paquete> paqueteRecuperado = this.paqueteService.findByTitulo("Conjunto boquita");
    assertEquals("Conjunto boquita", paqueteRecuperado.get().getTitulo());
  }
}
