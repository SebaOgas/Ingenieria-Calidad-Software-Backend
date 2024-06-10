package utn.ics.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.ics.entities.Variante;
import utn.ics.repositories.BaseRepository;
import utn.ics.repositories.VarianteRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class VarianteServiceImpl extends BaseServiceImpl<Variante, Long> implements VarianteService{

    @Autowired private final VarianteRepository varianteRepository;

    public VarianteServiceImpl (
            BaseRepository<Variante, Long> baseRepository, VarianteRepository varianteRepository){
        super(baseRepository);
        this.varianteRepository = varianteRepository;
    }

    @Override
    public Collection<Variante> filtrarNombre(String nombre) throws Exception {
        Collection<Variante> result = varianteRepository.filtrarNombre(nombre);
        if (result.isEmpty()) throw new Exception("No se encontraron variantes");
        return result;
    }

    @Override
    public Collection<Variante> filtrarPrincipal(String nomprod, Boolean principal) throws Exception {
        Collection<Variante> result = varianteRepository.filtrarPrincipal(nomprod, principal);
        if (result.isEmpty()) throw new Exception("No hay variante principal para el producto");
        return result;
    }
}
