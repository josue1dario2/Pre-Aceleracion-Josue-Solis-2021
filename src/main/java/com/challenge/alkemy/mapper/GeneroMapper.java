package com.challenge.alkemy.mapper;

import com.challenge.alkemy.dto.GeneroDto;
import com.challenge.alkemy.entity.GeneroEntity;
import com.challenge.alkemy.exception.SpringException;
import org.springframework.stereotype.Component;

@Component
public class GeneroMapper {

    public GeneroDto convertToDto(GeneroEntity entity) throws SpringException {
        GeneroDto dto = new GeneroDto();

        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setImagen(entity.getImagen());

        return dto;
    }

    public GeneroEntity convertToEntity(GeneroDto dto) throws SpringException{
        GeneroEntity entity = new GeneroEntity();

        entity.setNombre(dto.getNombre());
        entity.setImagen(dto.getImagen());

        return entity;
    }

}
