package fr.efrei.pokemon.controller;

import fr.efrei.pokemon.dto.CreateObject;
import fr.efrei.pokemon.dto.CreatePokemon;
import fr.efrei.pokemon.dto.UpdateObject;
import fr.efrei.pokemon.dto.UpdatePokemon;
import fr.efrei.pokemon.models.Object;
import fr.efrei.pokemon.models.Pokemon;
import fr.efrei.pokemon.services.ObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/objects")
public class ObjectController {

    private final ObjectService service; //rajout de final <=> const

    @Autowired
    public ObjectController(ObjectService service) {
        this.service = service;
    }

    //GET
    @GetMapping
    public ResponseEntity<List<Object>> findAll(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable String id){
        Object object = service.findById(id);
        if(object == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(object, HttpStatus.OK);
    }

    //POST
    @PostMapping
    public ResponseEntity<Object> create(@RequestBody CreateObject object){
        service.save(object);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //PATCH
    @PatchMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @RequestBody UpdateObject object){
        Object objectAModifier = service.findById(id);

        if(objectAModifier == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        service.update(id, object);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        Object object = service.findById(id);
        if(object == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
