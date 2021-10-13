package com.challenge.alkemy.mapper;

import com.challenge.alkemy.dto.PersonajeDto;
import com.challenge.alkemy.entity.PersonajeEntity;
import com.challenge.alkemy.exception.SpringException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonajeMapper {

    @Autowired
    private PeliculaMapper peliculaMapper;

    public PersonajeEntity convertToEntity(PersonajeDto dto)throws SpringException {
        PersonajeEntity entity = new PersonajeEntity();

        entity.setId(dto.getId());
        entity.setImagen(dto.getImagen());
        entity.setNombre(dto.getNombre());
        entity.setEdad(dto.getEdad());
        entity.setPeso(dto.getPeso());
        entity.setHistoria(dto.getHistoria());

        return entity;
    }

    public PersonajeDto convertToDto(PersonajeEntity entity,boolean loadPelicula) throws SpringException{
        PersonajeDto dto = new PersonajeDto();

        dto.setId(entity.getId());
        dto.setImagen(entity.getImagen());
        dto.setNombre(entity.getNombre());
        dto.setEdad(entity.getEdad());
        dto.setPeso(entity.getPeso());
        dto.setHistoria(entity.getHistoria());
        if(loadPelicula){
            dto.setPeliculas(peliculaMapper.convertToDtoList(entity.getPeliculas(),false));
        }

        return dto;
    }

    public PersonajeDto listaPersonaje(PersonajeEntity entity) {
        PersonajeDto dto = new PersonajeDto();
        dto.setImagen(entity.getImagen());
        dto.setNombre(entity.getNombre());

        return dto;
    }
    public List<PersonajeEntity> convertDtoToEntityList(List<PersonajeDto> dtos) throws SpringException {
        List<PersonajeEntity> personajes = new ArrayList<>();

        for(PersonajeDto dto : dtos){
            personajes.add(convertToEntity(dto));
        }
        return personajes;
    }
    public List<PersonajeDto> convertEntityToDtoList(List<PersonajeEntity> entities, boolean loadPeliculas) throws SpringException {
        List<PersonajeDto> dtos = new ArrayList<>();
        for(PersonajeEntity entity : entities){
            dtos.add(convertToDto(entity,loadPeliculas));
        }
        return dtos;
    }

    public void iconoEntityRefreshValues(PersonajeEntity entity,PersonajeDto dto){
        entity.setImagen(dto.getImagen());
        entity.setNombre(dto.getNombre());
        entity.setEdad(dto.getEdad());
        entity.setPeso(dto.getPeso());
        entity.setHistoria(dto.getHistoria());

    }
}
