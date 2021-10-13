package com.challenge.alkemy.controller;

import com.challenge.alkemy.dto.PersonajeDto;
import com.challenge.alkemy.exception.SpringException;
import com.challenge.alkemy.service.PersonajeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import static org.springframework.http.HttpStatus.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/personajes")
public class PersonajeController {

    @Autowired
    private PersonajeServiceImpl personajeService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.status(OK).body(personajeService.findAll());
        }catch (SpringException e){
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping(path = "/listaPersonajes")
    public ResponseEntity<?> getAllIcon(){
        try{
            return ResponseEntity.status(OK).body(personajeService.findAllPersonajes());
        }catch (SpringException e){
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping(path = "/filtros")
    public ResponseEntity<?> getDetailsByFilters(
            @RequestParam(required = false)String name,
            @RequestParam(required = false)String date,
            @RequestParam(required = false) Set<Long> cities,
            @RequestParam(required = false,defaultValue = "ASC")String order){
        try{
            List<PersonajeDto> iconos = personajeService.getByFilters(name,date,cities,order);
            return ResponseEntity.ok(iconos);
        }catch (SpringException e){
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        try{
            return ResponseEntity.status(OK).body(personajeService.findById(id));
        }catch (SpringException e){
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }

    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody PersonajeDto t){
        try{
            return ResponseEntity.status(OK).body(personajeService.save(t));
        }catch (SpringException e){
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody PersonajeDto t){
        try{
            return ResponseEntity.status(OK).body(personajeService.update(id,t));
        }catch (SpringException e){
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        try{
            personajeService.delete(id);
            return ResponseEntity.status(NO_CONTENT).build();
        }catch (SpringException e){
            return ResponseEntity.status(NOT_FOUND).build();
        }
    }

}
