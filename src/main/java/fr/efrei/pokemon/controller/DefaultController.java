package fr.efrei.pokemon.controller;


import fr.efrei.pokemon.models.Pokemon;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class DefaultController {

    @GetMapping
    public String greetings(){
        //Pokemon carapuce = new Pokemon("Carapuce", 10,100);

        return "Hello";



    }
}
