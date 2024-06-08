package utn.ics.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.ics.entities.Caracteristica;
import utn.ics.repositories.BaseRepository;
import utn.ics.repositories.CaracteristicaRepository;

@Service
public class CaracteristicaServiceImpl extends BaseServiceImpl<Caracteristica, Long> implements CaracteristicaService  {

    @Autowired
    private CaracteristicaRepository caracteristicaRepository;

    public CaracteristicaServiceImpl(BaseRepository<Caracteristica, Long> baseRepository, CaracteristicaRepository caracteristicaRepository) {
        super(baseRepository);
        this.caracteristicaRepository = caracteristicaRepository;
    }

}
