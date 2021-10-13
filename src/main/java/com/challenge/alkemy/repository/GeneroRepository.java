package com.challenge.alkemy.repository;

import com.challenge.alkemy.entity.GeneroEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface GeneroRepository extends JpaRepository<GeneroEntity,Long> {

    List<GeneroEntity> findByDenominacionIgnoreCase(String denominacion);

    List<GeneroEntity> findByFechaDeCreacion(String fecha);

    List<GeneroEntity> findByPeliculas(Long id);

    List<GeneroEntity> findAll(Specification<GeneroEntity> spec);

}
