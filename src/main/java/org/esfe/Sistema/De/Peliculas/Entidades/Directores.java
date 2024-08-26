package org.esfe.Sistema.De.Peliculas.Entidades;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

@Entity
@Table(name = "directores")
public class Directores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El nombre es requerido")
    private String nombre;

    @NotBlank(message = "El genero es requerido")
    private String genero;

    @NotBlank(message = "La nacionalidad es requerida")
    private String nacionalidad;

    @Nullable
    private LocalDate fechaNacimiento;

    @Nullable
    private LocalDate fechaFallecimiento;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotBlank(message = "El nombre es requerido") String getNombre() {
        return nombre;
    }

    public void setNombre(@NotBlank(message = "El nombre es requerido") String nombre) {
        this.nombre = nombre;
    }

    public @NotBlank(message = "El genero es requerido") String getGenero() {
        return genero;
    }

    public void setGenero(@NotBlank(message = "El genero es requerido") String genero) {
        this.genero = genero;
    }

    public @NotBlank(message = "La nacionalidad es requerida") String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(@NotBlank(message = "La nacionalidad es requerida") String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    @Nullable
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(@Nullable LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Nullable
    public LocalDate getFechaFallecimiento() {
        return fechaFallecimiento;
    }

    public void setFechaFallecimiento(@Nullable LocalDate fechaFallecimiento) {
        this.fechaFallecimiento = fechaFallecimiento;
    }
}
