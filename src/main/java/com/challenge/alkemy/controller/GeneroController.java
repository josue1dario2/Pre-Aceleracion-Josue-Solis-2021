package com.challenge.alkemy.controller;

import com.challenge.alkemy.dto.GeneroDto;
import com.challenge.alkemy.exception.SpringException;
import com.challenge.alkemy.service.GeneroServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/generos")
public class GeneroController {

    @Autowired
    private GeneroServiceImpl generoService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.status(OK).body(generoService.findAll());
        }catch (SpringException e){
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        try{
            return ResponseEntity.status(OK).body(generoService.findById(id));
        }catch (SpringException e){
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());

        }

    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody GeneroDto t){
        try{
            return ResponseEntity.status(CREATED).body(generoService.save(t));
        }catch (SpringException e){
            return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());

        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody GeneroDto t){
        try{
            return ResponseEntity.status(OK).body(generoService.update(id,t));
        }catch (SpringException e){
            return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            return ResponseEntity.status(NO_CONTENT).body(generoService.delete(id));
        }catch (SpringException e){
            return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());

        }
    }
}
