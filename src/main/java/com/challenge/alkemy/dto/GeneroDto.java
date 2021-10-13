package com.challenge.alkemy.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GeneroDto {

    private Long id;
    private String nombre;
    private String imagen;
}
