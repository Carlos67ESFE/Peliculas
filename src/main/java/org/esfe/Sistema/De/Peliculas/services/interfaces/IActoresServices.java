package org.esfe.Sistema.De.Peliculas.services.interfaces;

import org.esfe.Sistema.De.Peliculas.Entidades.Actores;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IActoresServices {
    Page<Actores> buscarTodosPaginados(Pageable pageable);

    List<Actores> obtenerTodos();

    Optional<Actores> buscarPorId(Integer id);

    Actores createOEditar(Actores actores);

    void eliminarPorId(Integer id);
}
