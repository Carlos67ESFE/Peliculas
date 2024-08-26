package org.esfe.Sistema.De.Peliculas.services.implementaciones;

import org.esfe.Sistema.De.Peliculas.Entidades.Directores;
import org.esfe.Sistema.De.Peliculas.repositorios.IDirectoresRepository;
import org.esfe.Sistema.De.Peliculas.services.interfaces.IDirectoresServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DirectoresServices implements IDirectoresServices {

    @Autowired
    private IDirectoresRepository directoresRepository;

    @Override
    public Page<Directores> buscarTodosPaginados(Pageable pageable) {
        return directoresRepository.findAll(pageable);
    }

    @Override
    public List<Directores> obtenerTodos() {
        return directoresRepository.findAll();
    }

    @Override
    public Optional<Directores> buscarPorId(Integer id) {
        return directoresRepository.findById(id);
    }

    @Override
    public Directores createOEditar(Directores directores) {
        return directoresRepository.save(directores);
    }

    @Override
    public void eliminarPorId(Integer id) {
        directoresRepository.deleteById(id);

    }
}
