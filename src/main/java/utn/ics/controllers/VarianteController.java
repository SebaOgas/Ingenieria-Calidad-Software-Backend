package utn.ics.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.ics.entities.Variante;
import utn.ics.services.VarianteServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "variante")
public class VarianteController extends BaseControllerImpl<Variante, VarianteServiceImpl>{

    @GetMapping("/{nombre}")
    public ResponseEntity<?> filtrarNombre(@PathVariable String nombre) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.filtrarNombre(nombre));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(("{\"error\": \"" + e.getMessage() + "\"}"));
        }
    }

    @GetMapping("/{nomprod}/{principal}")
    public ResponseEntity<?> getByNombre(@PathVariable String nomprod, @PathVariable Boolean principal) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.filtrarPrincipal(nomprod, principal));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(("{\"error\": \"" + e.getMessage() + "\"}"));
        }
    }
}
