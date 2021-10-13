package com.challenge.alkemy.mapper;

import com.challenge.alkemy.dto.PeliculaDto;
import com.challenge.alkemy.entity.PeliculaEntity;
import com.challenge.alkemy.exception.SpringException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Component
public class PeliculaMapper {

    @Autowired
    PersonajeMapper personajeMapper;

    public PeliculaEntity convertToEntity(PeliculaDto dto){
        PeliculaEntity entity = new PeliculaEntity();

        entity.setTitulo(dto.getTitulo());
        entity.setFechaDeCreacion(stringToLocalDate(dto.getFechaDeCreacion()));
        entity.setImagen(dto.getImagen());
        entity.setCalificacion(dto.getCalificacion());
        entity.setGeneroId(dto.getGeneroId());
        entity.setPersonajes(personajeMapper.convertDtoToEntityList(dto.getPersonajes()));

        return entity;
    }

    public PeliculaDto converToDto(PeliculaEntity entity,boolean loadPersonaje)throws SpringException{
        PeliculaDto dto = new PeliculaDto();

        dto.setId(entity.getId());
        dto.setTitulo(entity.getTitulo());
        dto.setImagen(entity.getImagen());
        dto.setFechaDeCreacion(entity.getFechaDeCreacion().toString());
        dto.setCalificacion(entity.getCalificacion());
        dto.setGeneroId(entity.getGeneroId());
        if(loadPersonaje){
            dto.setPersonajes(personajeMapper.convertEntityToDtoList(entity.getPersonajes(),false));
        }

        return dto;
    }

    public PeliculaDto convertToDtoCities(PeliculaEntity paisEntity) {
        PeliculaDto dto = new PeliculaDto();
        dto.setImagen(paisEntity.getImagen());
        dto.setTitulo(paisEntity.getTitulo());
        dto.setCalificacion(paisEntity.getCalificacion());

        return dto;
    }

    public List<PeliculaDto> convertToDtoList(List<PeliculaEntity> entities, boolean loadIcons) throws SpringException {
        List<PeliculaDto> dtos = new ArrayList<>();
        for(PeliculaEntity pelicula : entities){
            dtos.add(converToDto(pelicula,loadIcons));
        }
        return  dtos;
    }
    public List<PeliculaEntity> convertToEntityList(List<PeliculaDto> dtos){
        List<PeliculaEntity> peliculas = new ArrayList<>();
        for(PeliculaDto dto : dtos){
            peliculas.add(convertToEntity(dto));
        }
        return peliculas;
    }
    private LocalDate stringToLocalDate(String fecha){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(fecha,formato);
        return date;
    }

}
