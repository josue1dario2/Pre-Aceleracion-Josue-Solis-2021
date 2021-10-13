package com.challenge.alkemy.service;

import com.challenge.alkemy.exception.SpringException;

import java.util.List;
import java.util.Set;

public interface PersonajeService <T,ID>{

    public List<T> findAll()throws SpringException;

    public List<T> findAllPersonajes()throws SpringException;

    public T findById(ID id)throws SpringException;

    public T save(T entity)throws SpringException;

    public T update(ID id,T entity)throws SpringException;

    public void delete(ID id)throws SpringException;


}
