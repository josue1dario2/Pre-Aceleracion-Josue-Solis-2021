package com.challenge.alkemy.service;

import com.challenge.alkemy.dto.PeliculaDto;
import com.challenge.alkemy.dto.PeliculaFilterDto;
import com.challenge.alkemy.dto.PersonajeDto;
import com.challenge.alkemy.dto.PersonajeFilterDto;
import com.challenge.alkemy.entity.PeliculaEntity;
import com.challenge.alkemy.entity.PersonajeEntity;
import com.challenge.alkemy.mapper.PeliculaMapper;
import com.challenge.alkemy.exception.SpringException;
import com.challenge.alkemy.mapper.PersonajeMapper;
import com.challenge.alkemy.repository.PersonajeRepository;
import com.challenge.alkemy.repository.GeneroRepository;
import com.challenge.alkemy.repository.PeliculaRepository;
import com.challenge.alkemy.service.especificacion.PeliculaEspecificacion;
import com.challenge.alkemy.service.especificacion.PersonajeEspecificacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class PeliculaServiceImpl implements PeliculaService <PeliculaDto,Long>{

    @Autowired
    private PersonajeServiceImpl personajeService;

    @Autowired
    private PeliculaRepository peliculaRepository;

    @Autowired
    private PersonajeRepository personajeRepository;

    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private PeliculaMapper peliculaMapper;

    @Autowired
    private PersonajeMapper personajeMapper;

    @Autowired
    private PeliculaEspecificacion peliculaEspecificacion;

    private static String ERROR_1 = "No hay peliculas en la base de datos";
    private static String ERROR_2 = "La pelicula no esta en la base de datos";
    private static String ERROR_3 = "La denominación ingresada no existe en la base de datos";
    private static String ERROR_4 = "El id ingresado no tiene personajes vinculados";
    private static String ERROR_5 = "El orden ingresado no es válido";


    @Override
    @Transactional
    public List<PeliculaDto> findAll() throws SpringException {
        try{
            List<PeliculaEntity> paises  = peliculaRepository.findAll();
            if(paises.isEmpty()){
                throw new SpringException(ERROR_1);
            }
            List<PeliculaDto> paisesDto = peliculaMapper.convertToDtoList(paises,true);
            return paisesDto;

        }catch (SpringException e){
            throw new SpringException(e.getMessage());
        }
    }
    @Transactional
    public List<PersonajeDto> getByFilters(String nombre, Long idGenero,String order) throws SpringException {
        PeliculaFilterDto filterDto = new PeliculaFilterDto(nombre, idGenero, order);
        List<PersonajeEntity> entities = personajeRepository.findAll(peliculaEspecificacion.getByFilters(filterDto));
        List<PersonajeDto> dtos = personajeMapper.convertEntityToDtoList(entities,true);
        return dtos;
    }

    @Override
    public List<PeliculaDto> findCities() throws SpringException {
        try{
            List<PeliculaEntity> paises  = peliculaRepository.findAll();
            if(paises.isEmpty()){
                throw new SpringException(ERROR_1);
            }
            List<PeliculaDto> paisesDto = new ArrayList<>();

            for(PeliculaEntity pais : paises){
                paisesDto.add(peliculaMapper.convertToDtoCities(pais));
            }

            return paisesDto;
        }catch (SpringException e){
            throw new SpringException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public PeliculaDto findById(Long id) throws SpringException {
        try{
            Optional<PeliculaEntity> entity = peliculaRepository.findById(id);

            if(!entity.isPresent()){
                throw new SpringException(ERROR_2);
            }
            return peliculaMapper.converToDto(entity.get(),false);

        }catch (SpringException e){
            throw new SpringException(e.getMessage());
        }
    }

    @Override
    public List<PeliculaDto> findByIdContinente(Long continenteId) throws SpringException {
        try{
            List<PeliculaEntity> paises  = peliculaRepository.findByIdContinente(continenteId);
            List<PeliculaDto> paisesDto = new ArrayList<>();

            if(paises.isEmpty()){
                throw new SpringException(ERROR_4);
            }
            for(PeliculaEntity pais : paises){
                paisesDto.add(peliculaMapper.convertToDtoCities(pais));
            }

            return paisesDto;
        }catch (SpringException e){
            throw new SpringException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public PeliculaDto save(PeliculaDto paisDto) throws SpringException {
        try{
            PeliculaEntity entity = peliculaMapper.convertToEntity(paisDto);
            peliculaRepository.save(entity);

            return peliculaMapper.converToDto(entity,false);

        }catch (SpringException e){
            throw new SpringException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public PeliculaDto update(Long id, PeliculaDto paisDto) throws SpringException {
        try{
            Optional<PeliculaEntity> pais = peliculaRepository.findById(id);

            if(!pais.isPresent()){
                throw new SpringException(ERROR_2);
            }
            PeliculaEntity entity = peliculaMapper.convertToEntity(paisDto);
            peliculaRepository.save(entity);
            return peliculaMapper.converToDto(pais.get(),false);

        }catch (SpringException e){
            throw new SpringException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean delete(Long id) throws SpringException {
        try{
            if(peliculaRepository.existsById(id)){
                peliculaRepository.deleteById(id);
                return true;
            }else{
                throw new SpringException(ERROR_2);
            }
        }catch (SpringException e){
            throw new SpringException(e.getMessage());
        }
    }
    @Override
    public void addPersonaje(Long id, Long idPersonaje) throws SpringException {
        try{
            Optional<PeliculaEntity> pais = peliculaRepository.findById(id);
            if(!pais.isPresent()){
                throw new SpringException(ERROR_2);
            }
            PeliculaEntity peliculaEntity = pais.get();
            peliculaEntity.getPersonajes().size();
            PersonajeDto personajeDto = personajeService.findById(idPersonaje);
            peliculaEntity.addPersonaje(personajeMapper.convertToEntity(personajeDto));
            peliculaRepository.save(peliculaEntity);

        }catch (SpringException e){
            throw new SpringException(e.getMessage());
        }
    }

    @Override
    public void removePersonaje(Long id, Long idPersonaje) throws SpringException {
        try{
            Optional<PeliculaEntity> pais = peliculaRepository.findById(id);
            if(!pais.isPresent()){
                throw new SpringException(ERROR_2);
            }
            PeliculaEntity peliculaEntity = pais.get();
            peliculaEntity.getPersonajes().size();
            PersonajeDto iconoDto = personajeService.findById(idPersonaje);
            peliculaEntity.removePersonaje(personajeMapper.convertToEntity(iconoDto));
            peliculaRepository.save(peliculaEntity);

        }catch (SpringException e){
            throw new SpringException(e.getMessage());
        }
    }

}
