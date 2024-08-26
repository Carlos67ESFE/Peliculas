package org.esfe.Sistema.De.Peliculas.services.interfaces;

import org.esfe.Sistema.De.Peliculas.Entidades.Actores;
import org.esfe.Sistema.De.Peliculas.Entidades.Directores;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface IDirectoresServices {

    Page<Directores> buscarTodosPaginados(Pageable pageable);

    List<Directores> obtenerTodos();

    Optional<Directores> buscarPorId(Integer id);

    Directores createOEditar(Directores directores);

    void eliminarPorId(Integer id);
}
