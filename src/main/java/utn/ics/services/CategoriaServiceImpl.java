package utn.ics.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.ics.entities.Categoria;
import utn.ics.repositories.BaseRepository;
import utn.ics.repositories.CategoriaRepository;

import java.util.Optional;

@Service
public class CategoriaServiceImpl extends BaseServiceImpl<Categoria,Long> implements CategoriaService {
    @Autowired
    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(
            BaseRepository<Categoria, Long> baseRepository,
            CategoriaRepository categoriaRepository) {
        super(baseRepository);
        this.categoriaRepository = categoriaRepository;
    }

    public Optional<Categoria> findByTitulo(String titulo) {
        return this.categoriaRepository.findByTitulo(titulo);
    }
}
