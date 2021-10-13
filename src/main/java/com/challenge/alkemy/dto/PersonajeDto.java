package com.challenge.alkemy.dto;

import com.challenge.alkemy.entity.PeliculaEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonajeDto {

    private Long id;
    private String imagen;
    private String nombre;
    private Integer edad;
    private Double peso;
    private String historia;
    private List<PeliculaEntity> peliculas = new ArrayList<>();

}
