package utn.ics.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.ics.entities.Marca;
import utn.ics.services.MarcaServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "marca")
public class MarcaController extends BaseControllerImpl<Marca, MarcaServiceImpl> {

    @GetMapping("/{nombre}")
    public ResponseEntity<?> getByNombre(@PathVariable String nombre) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.getByNombre(nombre));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
        }
    }
}
