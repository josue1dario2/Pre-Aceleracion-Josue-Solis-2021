package com.challenge.alkemy.service;

import com.challenge.alkemy.dto.GeneroDto;
import com.challenge.alkemy.entity.GeneroEntity;
import com.challenge.alkemy.mapper.GeneroMapper;
import com.challenge.alkemy.exception.SpringException;
import com.challenge.alkemy.repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class GeneroServiceImpl implements GeneroService <GeneroDto,Long> {

    private final String ERROR_3 = "Error in server";
    private static String ERROR_1 = "No hay géneros en la base de datos";
    private static String ERROR_2 = "El género no esta en la base de datos";

    @Autowired
    private GeneroRepository genreRepository;
    @Autowired
    private GeneroMapper genreConverter;

    @Override
    @Transactional
    public GeneroDto save(GeneroDto genreDto) throws SpringException {
        try {

            GeneroEntity genre = genreConverter.convertToEntity(genreDto);
            genreRepository.save(genre);

            return genreConverter.convertToDto(genre);

        }catch (Exception e){
            throw new SpringException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public GeneroDto update(Long id,GeneroDto genreDto) throws SpringException {
        try {
            Optional<GeneroEntity> genre = genreRepository.findById(id);

            if (!genre.isPresent()) {
                throw new SpringException(ERROR_2);
            }
            GeneroEntity entity = genreConverter.convertToEntity(genreDto);
            genreRepository.save(entity);

            return genreConverter.convertToDto(genre.get());

        }catch (Exception e){
            throw new SpringException(ERROR_1);
        }
    }

    @Override
    @Transactional
    public List<GeneroDto> findAll() throws SpringException {
        try{
            List<GeneroDto> genreDtos = new ArrayList<>();
            List<GeneroEntity> generos = genreRepository.findAll();

            if(generos.isEmpty()){
                throw new SpringException(ERROR_1);
            }

            for(GeneroEntity genre : generos){
                genreDtos.add(genreConverter.convertToDto(genre));
            }
            return genreDtos;

        }catch (Exception e){
            throw new SpringException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public GeneroDto findById(Long id) throws SpringException {
        try{
            Optional<GeneroEntity> genre = genreRepository.findById(id);

            if(!genre.isPresent()){
                throw new SpringException(ERROR_2);
            }

            return genreConverter.convertToDto(genre.get());

        }catch (Exception e){
            throw new SpringException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean delete(Long id) throws SpringException {
        try {
            if (genreRepository.existsById(id)) {
                genreRepository.deleteById(id);
                return true;
            } else {
                throw new SpringException(ERROR_2);
            }

        }catch (Exception e){
            throw new SpringException(e.getMessage());
        }
    }
}
