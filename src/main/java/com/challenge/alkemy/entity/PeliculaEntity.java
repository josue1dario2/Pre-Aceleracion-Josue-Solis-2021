package com.challenge.alkemy.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "pelicula")
@SQLDelete(sql = "UPDATE pelicula SET deleted = true WHERE id=? ")
@Where(clause = "deleted=false")
public class PeliculaEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column
        private String imagen;
        @Column
        private String titulo;
        @DateTimeFormat(pattern = "yyyy/MM/dd")
        private LocalDate fechaDeCreacion;
        @Column
        private Integer calificacion;

        private boolean deleted = Boolean.FALSE;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "genero_id",insertable = false,updatable = false)
    private GeneroEntity genero;

    @Column(name = "genero_id",nullable = false)
    private Long generoId;


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinTable(
            name = "pelicula_personaje",
            joinColumns = @JoinColumn(name = "pelicula_id"),
            inverseJoinColumns = @JoinColumn(name = "personaje_id")
    )
    private List<PersonajeEntity> personajes = new ArrayList<>();

    public void addPersonaje(PersonajeEntity personaje){

        this.personajes.add(personaje);
    }
    public void removePersonaje(PersonajeEntity personaje){

        this.personajes.remove(personaje);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final PeliculaEntity other = (PeliculaEntity) obj;
        return other.id == this.id;
    }



}
