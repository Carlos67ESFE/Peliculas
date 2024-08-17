package org.esfe.Sistema.De.Peliculas.services.implementaciones;

import org.esfe.Sistema.De.Peliculas.Entidades.Generos;
import org.esfe.Sistema.De.Peliculas.repositorios.IGenerosRepository;
import org.esfe.Sistema.De.Peliculas.services.interfaces.IGenerosServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenerosServices implements IGenerosServices {

    @Autowired
    private IGenerosRepository generosRepository;

    @Override
    public Page<Generos> buscarTodosPaginados(Pageable pageable) {
        return generosRepository.findAll(pageable);
    }

    @Override
    public List<Generos> obtenerTodos() {
        return generosRepository.findAll();
    }

    @Override
    public Optional<Generos> buscarPorId(Integer id) {
        return generosRepository.findById(id);
    }

    @Override
    public Generos createOEditar(Generos generos) {
        return generosRepository.save(generos);
    }

    @Override
    public void eliminarPorId(Integer id) {
        generosRepository.deleteById(id);

    }
}
