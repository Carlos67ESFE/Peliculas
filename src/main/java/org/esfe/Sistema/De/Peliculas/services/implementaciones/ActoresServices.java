package org.esfe.Sistema.De.Peliculas.services.implementaciones;

import org.esfe.Sistema.De.Peliculas.Entidades.Actores;
import org.esfe.Sistema.De.Peliculas.repositorios.IActoresRepository;
import org.esfe.Sistema.De.Peliculas.services.interfaces.IActoresServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActoresServices implements IActoresServices {

    @Autowired
    private IActoresRepository actoresRepository;

    @Override
    public Page<Actores> buscarTodosPaginados(Pageable pageable) {
        return actoresRepository.findAll(pageable);
    }

    @Override
    public List<Actores> obtenerTodos() {
        return actoresRepository.findAll();
    }

    @Override
    public Optional<Actores> buscarPorId(Integer id) {
        return actoresRepository.findById(id);
    }

    @Override
    public Actores createOEditar(Actores actores) {
        return actoresRepository.save(actores);
    }

    @Override
    public void eliminarPorId(Integer id) {
        actoresRepository.deleteById(id);

    }
}
