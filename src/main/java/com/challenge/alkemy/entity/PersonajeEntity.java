package com.challenge.alkemy.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "personaje")
@SQLDelete(sql = "UPDATE personaje SET deleted = true WHERE id=? ")
@Where(clause = "deleted=false")
public class PersonajeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String imagen;
    @Column
    private String nombre;
    @Column
    private Integer edad;
    @Column
    private Double peso;
    @Column
    private String historia;

    private boolean deleted = Boolean.FALSE;

    @ManyToMany(mappedBy = "personajes",cascade = CascadeType.ALL)
    private List<PeliculaEntity> peliculas = new ArrayList<>();

}
