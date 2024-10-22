package fr.efrei.pokemon.controller;

import fr.efrei.pokemon.models.Pokemon;
import fr.efrei.pokemon.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pokemons")
public class PokemonController {

    private final PokemonService service; //rajout de final <=> const

    @Autowired
    public PokemonController(PokemonService service) {
        this.service = service;
    }

    //GET
    @GetMapping
    public ResponseEntity<List<Pokemon>> findAll(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    /*public List<Pokemon> findAll(){
        return service.findAll();
    }*/

    @GetMapping("/{id}") // /pokemons/id
    public ResponseEntity<Pokemon> findById(@PathVariable String id){
        Pokemon pokemon = service.findById(id);
        if(pokemon == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pokemon, HttpStatus.OK);
    }

    //POST
    @PostMapping
    public ResponseEntity<Pokemon> create(@RequestBody Pokemon pokemon){
        service.save(pokemon);
        return new ResponseEntity<>(pokemon, HttpStatus.CREATED);
    }

    /*public void create(@RequestBody Pokemon pokemon){
        service.save(pokemon);
    }*/

    //PUT
    @PutMapping("/{id}")
    public ResponseEntity<Pokemon> update(@PathVariable String id, @RequestBody Pokemon pokemon){
        Pokemon pokemonAModifier = service.findById(id);

        if(pokemonAModifier == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        service.update(id, pokemon);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        Pokemon pokemon = service.findById(id);
        if(pokemon == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //PATCH
    @PatchMapping("/{id}")
    public ResponseEntity<Pokemon> patchUpdate(@PathVariable String id, @RequestBody Pokemon pokemon){
        Pokemon pokemonAModifier = service.findById(id);

        if(pokemonAModifier == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        service.update(id, pokemon);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
