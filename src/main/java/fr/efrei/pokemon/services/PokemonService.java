package fr.efrei.pokemon.services;

import fr.efrei.pokemon.models.Pokemon;
import fr.efrei.pokemon.repositories.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PokemonService {

    private final PokemonRepository pokemonRepository;

    @Autowired
    private PokemonService(PokemonRepository pokemonRepository){
        this.pokemonRepository = pokemonRepository;
    }

    //Trajet de la donnée
    // BDD -> Entity -> Repository -> Service -> Controller
    public List<Pokemon> findAll(){
        return pokemonRepository.findAll();
    }

    public Pokemon findById(String id){
        //Optional : soit objet soit null
        return pokemonRepository.findById(id).orElse(null);
    }


    //Trajet de la donnée
    // Controller -> Service -> Repository -> Entité -> BDD
    public void save(Pokemon pokemon){
        pokemonRepository.save(pokemon);
    }

    public void delete(String id){
        pokemonRepository.deleteById(id);
    }

    public void update(String id, Pokemon pokemonBody) {
        Pokemon pokemonAModifier = findById(id);
        pokemonAModifier.setHp(pokemonBody.getHp());
        pokemonAModifier.setName(pokemonBody.getName());
        pokemonAModifier.setLevel(pokemonBody.getLevel());
        pokemonRepository.save(pokemonAModifier);
    }

    public void partialUpdate(String id, Pokemon pokemonBody){
        Pokemon pokemonAModifier = findById(id);
        if(pokemonAModifier.getHp() != 0){
            pokemonAModifier.setHp(pokemonBody.getHp());
        }
        if(pokemonAModifier.getLevel() != 0){
            pokemonAModifier.setLevel(pokemonBody.getLevel());
        }
        if(pokemonAModifier.getName() != null){
            pokemonAModifier.setName(pokemonBody.getName());
        }
        pokemonRepository.save(pokemonAModifier);
    }
}
