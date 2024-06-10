package utn.ics.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utn.ics.entities.Paquete;
import utn.ics.services.PaqueteServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "paquetes")
public class PaqueteControllerImpl extends BaseControllerImpl<Paquete, PaqueteServiceImpl>{
}
