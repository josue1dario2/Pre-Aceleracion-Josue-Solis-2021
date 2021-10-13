package com.challenge.alkemy.service;

import com.challenge.alkemy.exception.SpringException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface PeliculaService  <T,ID>{

    public List<T> findAll()throws SpringException;

    public List<T> findCities()throws SpringException;

    public T findById(ID id)throws SpringException;

    public List<T> findByIdContinente(Long continenteId)throws SpringException;
    
    public T save(T entity)throws SpringException;

    public T update(ID id,T entity)throws SpringException;

    public boolean delete(ID id)throws SpringException;

    void addPersonaje(Long id,Long idIcono)throws SpringException;

    void removePersonaje(Long id,Long idIcono)throws SpringException;
}
