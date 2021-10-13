package com.challenge.alkemy.dto;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class PersonajeFilterDto {
    private String nombre;
    private String edad;
    private Set<Long> peliculas = new HashSet<>();

    public PersonajeFilterDto(String nombre, String edad, Set<Long> peliculas) {
        this.nombre = nombre;
        this.edad = edad;
        this.peliculas = peliculas;
    }

}
