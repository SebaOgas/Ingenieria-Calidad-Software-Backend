package utn.ics.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utn.ics.entities.Caracteristica;
import utn.ics.services.CaracteristicaServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "caracteristica")
public class CaracteristicaController extends BaseControllerImpl<Caracteristica, CaracteristicaServiceImpl> {
}
