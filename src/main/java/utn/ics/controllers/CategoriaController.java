package utn.ics.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utn.ics.entities.Categoria;
import utn.ics.services.CategoriaServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "categoria")
public class CategoriaController extends BaseControllerImpl<Categoria, CategoriaServiceImpl> {
}
