package utn.ics.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.ics.entities.Marca;
import utn.ics.repositories.BaseRepository;
import utn.ics.repositories.MarcaRepository;

import java.util.Optional;

@Service
public class MarcaServiceImpl extends BaseServiceImpl<Marca, Long> implements MarcaService  {

    @Autowired
    private MarcaRepository marcaRepository;

    public MarcaServiceImpl(BaseRepository<Marca, Long> baseRepository, MarcaRepository marcaRepository) {
        super(baseRepository);
        this.marcaRepository = marcaRepository;
    }

    @Override
    public Marca getByNombre(String nombre) throws Exception {
        Optional<Marca> optionalMarca = marcaRepository.findByNombre(nombre);
        if(optionalMarca.isEmpty()) throw new Exception("No se encontró la marca");
        return optionalMarca.get();
    }
}
