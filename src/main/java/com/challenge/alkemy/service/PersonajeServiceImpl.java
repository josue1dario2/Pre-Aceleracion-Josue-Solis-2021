package com.challenge.alkemy.service;

import com.challenge.alkemy.dto.PersonajeDto;
import com.challenge.alkemy.dto.PersonajeFilterDto;
import com.challenge.alkemy.entity.PersonajeEntity;
import com.challenge.alkemy.mapper.PersonajeMapper;
import com.challenge.alkemy.exception.SpringException;
import com.challenge.alkemy.repository.PersonajeRepository;
import com.challenge.alkemy.repository.PeliculaRepository;
import com.challenge.alkemy.service.especificacion.PersonajeEspecificacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class PersonajeServiceImpl implements PersonajeService <PersonajeDto,Long>{

    @Autowired
    private PeliculaServiceImpl peliculaService;

    @Autowired
    private PersonajeRepository personajeRepository;

    @Autowired
    private PeliculaRepository peliculaRepository;

    @Autowired
    private PersonajeMapper personajeMapper;

    @Autowired
    private PersonajeEspecificacion personajeEspecificacion;

    private static String ERROR_1 = "No hay íconos en la base de datos";
    private static String ERROR_2 = "El ícono no esta en la base de datos";
    private static String ERROR_3 = "La denominación ingresada no existe";
    private static String ERROR_4 = "La fecha introducida no existe";
    private static String ERROR_5 = "La pais_id introducido no existe";

    @Override
    @Transactional
    public List<PersonajeDto> findAll() throws SpringException {
        try{
            List<PersonajeEntity> personajes  = personajeRepository.findAll();
            if(personajes.isEmpty()){
                throw new SpringException(ERROR_1);
            }
            List<PersonajeDto> iconosDtos = personajeMapper.convertEntityToDtoList(personajes,false);
            return iconosDtos;

        }catch (SpringException e){
            throw new SpringException(e.getMessage());
        }
    }
    @Transactional
    public List<PersonajeDto> getByFilters(String name, String date, Set<Long> cities, String order) throws SpringException {
        PersonajeFilterDto filtersDTO = new PersonajeFilterDto(name, date, cities, order);
        List<PersonajeEntity> entities = personajeRepository.findAll(personajeEspecificacion.getByFilters(filtersDTO));
        List<PersonajeDto> dtos = personajeMapper.convertEntityToDtoList(entities,true);
        return dtos;
    }

    @Override
    public List<PersonajeDto> findAllPersonajes() throws SpringException {
        try{
            List<PersonajeEntity> personajes  = personajeRepository.findAll();
            List<PersonajeDto> personajeDtos = new ArrayList<>();

            if(personajes.isEmpty()){
                throw new SpringException(ERROR_1);
            }
            for(PersonajeEntity personaje : personajes){
                personajeDtos.add(personajeMapper.listaPersonaje(personaje));
            }
            return personajeDtos;

        }catch (SpringException e){
            throw new SpringException(e.getMessage());
        }
    }

    @Override
    public List<PersonajeDto> getDenominacion(String denominacion) throws SpringException {
        try{
            List<PersonajeDto> listaDto = new ArrayList<>();
            List<PersonajeEntity> lista = personajeRepository.findByDenominacionIgnoreCase(denominacion);
            if(lista.isEmpty()){
                throw new SpringException(ERROR_3);
            }
            for(PersonajeEntity entity : lista){
                listaDto.add(personajeMapper.convertToDto(entity,false));
            }
            return listaDto;
        }catch (SpringException e){
            throw new SpringException(e.getMessage());
        }
    }

    @Override
    public List<PersonajeDto> getDate(String fecha) throws SpringException {
        try{
            List<PersonajeDto> listaDto = new ArrayList<>();
            List<PersonajeEntity> lista = personajeRepository.findByFechaDeCreacion(fecha);
            if(lista.isEmpty()){
                throw new SpringException(ERROR_4);
            }
            for(PersonajeEntity entity : lista){
                listaDto.add(personajeMapper.convertToDto(entity,false));
            }
            return listaDto;
        }catch (SpringException e){
            throw new SpringException(e.getMessage());
        }
    }

    @Override
    public List<PersonajeDto> getCities(Long idPelicula) throws SpringException {
        try{
            List<PersonajeDto> listaDto = new ArrayList<>();
            List<PersonajeEntity> lista = personajeRepository.findByPelicula(idPelicula);
            if(listaDto.isEmpty()){
                throw new SpringException(ERROR_5);
            }
            for(PersonajeEntity entity : lista){
                listaDto.add(personajeMapper.convertToDto(entity,false));
            }
            return listaDto;

        }catch (SpringException e){
            throw new SpringException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public PersonajeDto findById(Long id) throws SpringException {
        try{
            Optional<PersonajeEntity> personaje = personajeRepository.findById(id);
            if(!personaje.isPresent()){
                throw new SpringException(ERROR_2);
            }
            return personajeMapper.convertToDto(personaje.get(),false);

        }catch (SpringException e){
            throw new SpringException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public PersonajeDto save(PersonajeDto dto) throws SpringException {
        try{
            PersonajeEntity icono = personajeMapper.convertToEntity(dto);
            personajeRepository.save(icono);

            return personajeMapper.convertToDto(icono,false);

        }catch (SpringException e){
            throw new SpringException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public PersonajeDto update(Long id, PersonajeDto dto) throws SpringException {
        try{
            Optional<PersonajeEntity> personaje = personajeRepository.findById(id);

            if(!personaje.isPresent()){
                throw new SpringException(ERROR_2);
            }
            PersonajeEntity entity = personajeMapper.convertToEntity(dto);
            personajeRepository.save(entity);

            return personajeMapper.convertToDto(personaje.get(),false);

        }catch (SpringException e){
            throw new SpringException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void delete(Long id) throws SpringException {
        try{
            if(personajeRepository.existsById(id)){
                personajeRepository.deleteById(id);
            }else{
                throw new SpringException(ERROR_2);
            }
        }catch (SpringException e){
            throw new SpringException(e.getMessage());
        }
    }

}
