package com.challenge.alkemy.repository;

import com.challenge.alkemy.entity.PersonajeEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PersonajeRepository extends JpaRepository<PersonajeEntity,Long> {

    //List<PersonajeEntity> findByPelicula(Long id);

    List<PersonajeEntity> findAll(Specification<PersonajeEntity> spec);

}
