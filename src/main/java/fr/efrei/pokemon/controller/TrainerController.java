package fr.efrei.pokemon.controller;

import fr.efrei.pokemon.dto.CreateTrainer;
import fr.efrei.pokemon.dto.UpdateTrainer;
import fr.efrei.pokemon.models.Pokemon;
import fr.efrei.pokemon.models.Trainer;
import fr.efrei.pokemon.services.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trainers")
public class TrainerController {

    private final TrainerService service;
    private final TrainerService trainerService;

    @Autowired
    public TrainerController(TrainerService service, TrainerService trainerService) {
        this.service = service;
        this.trainerService = trainerService;
    }

    //GET
    @GetMapping
    public ResponseEntity<List<Trainer>> findAll(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}") // /pokemons/id
    public ResponseEntity<Trainer> findById(@PathVariable String id){
        Trainer trainer = service.findById(id);
        if(trainer == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(trainer, HttpStatus.OK);
    }

    //POST
    @PostMapping
    public ResponseEntity<Trainer> create(@RequestBody CreateTrainer trainer){
        service.save(trainer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody UpdateTrainer trainerBody) {
        Trainer trainer = trainerService.findById(id);
        if (trainer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        trainerService.update(id, trainerBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        Trainer trainer = trainerService.findById(id);
        if(trainer == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        trainerService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}