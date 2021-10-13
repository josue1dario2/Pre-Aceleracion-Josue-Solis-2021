package com.challenge.alkemy.repository;

import com.challenge.alkemy.entity.PeliculaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PeliculaRepository extends JpaRepository<PeliculaEntity,Long> {

    @Query(value = "SELECT * FROM pelicula p WHERE p.generoId = ?1 ",nativeQuery = true)
    List<PeliculaEntity> findByIdContinente(Long generoId);



}
