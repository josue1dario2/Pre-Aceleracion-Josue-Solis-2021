package com.challenge.alkemy.controller;

import com.challenge.alkemy.dto.PeliculaDto;
import com.challenge.alkemy.dto.PersonajeDto;
import com.challenge.alkemy.exception.SpringException;
import com.challenge.alkemy.service.PeliculaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import static org.springframework.http.HttpStatus.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/movies")
public class PeliculaController {

    @Autowired
    private PeliculaServiceImpl peliculaService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.status(OK).body(peliculaService.findAll());
        }catch (SpringException e){
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping(path = "cities")
    public ResponseEntity<?> getAllCities(){
        try{
            return ResponseEntity.status(OK).body(peliculaService.findCities());
        }catch (SpringException e){
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        try{
            return ResponseEntity.status(OK).body(peliculaService.findById(id));
        }catch (SpringException e){
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }

    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody PeliculaDto t){
        try{
            return ResponseEntity.status(OK).body(peliculaService.save(t));
        }catch (SpringException e){
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody PeliculaDto t){
        try{
            return ResponseEntity.status(OK).body(peliculaService.update(id,t));
        }catch (SpringException e){
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            return ResponseEntity.status(NO_CONTENT).body(peliculaService.delete(id));
        }catch (SpringException e){
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping(path = "/filtros")
    public ResponseEntity<?> getDetailsByFilters(
            @RequestParam(required = false)String nombre,
            @RequestParam(required = false) Long idGenero,
            @RequestParam(required = false,defaultValue = "ASC")String order){
        try{
            List<PersonajeDto> iconos = peliculaService.getByFilters(nombre,idGenero,order);
            return ResponseEntity.ok(iconos);
        }catch (SpringException e){
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }

    }


    @PostMapping(path = "/{id}/personaje/{idPersonaje}")
    public ResponseEntity<Void> addPersonaje(@PathVariable Long id,@PathVariable Long idPersonaje){
        try{
            peliculaService.addPersonaje(id,idPersonaje);
            return ResponseEntity.status(CREATED).build();
        }catch (SpringException e){
            return ResponseEntity.status(BAD_REQUEST).build();
        }
    }
    @DeleteMapping(path = "/{id}/personaje/{idPersonaje}")
    public ResponseEntity<Void> removePersonaje(@PathVariable Long id,@PathVariable Long idPersonaje){
        try{
            peliculaService.removePersonaje(id,idPersonaje);
            return ResponseEntity.status(NO_CONTENT).build();
        }catch (SpringException e){
            return ResponseEntity.status(BAD_REQUEST).build();
        }
    }

}
