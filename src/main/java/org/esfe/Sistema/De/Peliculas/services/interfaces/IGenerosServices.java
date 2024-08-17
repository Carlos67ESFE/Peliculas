package org.esfe.Sistema.De.Peliculas.services.interfaces;

import org.esfe.Sistema.De.Peliculas.Entidades.Generos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IGenerosServices {
    Page<Generos> buscarTodosPaginados(Pageable pageable);

    List<Generos> obtenerTodos();

    Optional<Generos> buscarPorId(Integer id);

    Generos createOEditar(Generos generos);

    void eliminarPorId(Integer id);

}
