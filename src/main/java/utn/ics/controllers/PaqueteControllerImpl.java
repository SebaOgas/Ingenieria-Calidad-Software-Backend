package utn.ics.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.ics.entities.Paquete;
import utn.ics.services.PaqueteServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/paquetes")
public class PaqueteControllerImpl extends BaseControllerImpl<Paquete, PaqueteServiceImpl> {

  @GetMapping("/nombre={nombre}")
  public ResponseEntity<?> findPaqueteByTitulo(@PathVariable String nombre) {
    try {
      return ResponseEntity.status(HttpStatus.OK).body(servicio.findByTitulo(nombre));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(("{\"error\": \"" + e.getMessage() + "\"}"));
    }
  }
}
