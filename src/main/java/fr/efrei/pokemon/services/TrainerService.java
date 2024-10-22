package fr.efrei.pokemon.services;

import fr.efrei.pokemon.dto.CreateTrainer;
import fr.efrei.pokemon.dto.UpdateTrainer;
import fr.efrei.pokemon.models.Trainer;
import fr.efrei.pokemon.models.Pokemon;
import fr.efrei.pokemon.repositories.PokemonRepository;
import fr.efrei.pokemon.repositories.TrainerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrainerService {

    private final TrainerRepository trainerRepository;
    private final PokemonRepository pokemonRepository;
    private final PokemonService pokemonService;

    @Autowired
    public TrainerService(TrainerRepository trainerRepository, PokemonRepository pokemonRepository, PokemonService pokemonService) {
        this.trainerRepository = trainerRepository;
        this.pokemonRepository = pokemonRepository;
        this.pokemonService = pokemonService;
    }

    public Trainer findById(String id){
        return trainerRepository.findById(id).orElse(null);
    }

    public List<Trainer> findAll(){
        return trainerRepository.findAll();
    }

    public void save(CreateTrainer trainerBody){
        Trainer trainer = new Trainer();
        trainer.setName(trainerBody.getName());
        //on recupère la liste des ids des pokemons en body postman
        List<String> pokemonIds = trainerBody.getTeam();
        //On déclare une nouvelle liste de pokemon
        List<Pokemon> pokemonAAjouter = new ArrayList<>();
        //Pour chaque id de pokemon dans ma list d'id
        for(String pokemonId : pokemonIds){
            //je recupère le pokemon avec l'id courant
            Pokemon pokemon = pokemonService.findById(pokemonId);
            //si pokemon existe, je l'ajoute à ma list de pokemon
            if(pokemon != null){
                pokemonAAjouter.add(pokemon);
            }

        }
        //pokemonIds.forEach(pokemonId -> pokemonService.findById(pokemonId)); //autre methode que for
        //j'applique la list de pokemon au trainer que j'ai créé
        trainer.setTeam(pokemonAAjouter);
        trainerRepository.save(trainer);
    }

    @Transactional
    public void update(String id, UpdateTrainer trainerBody) {
        Trainer trainer = findById(id);
        if (trainerBody.getName() != null) {
            trainer.setName(trainerBody.getName());
        }
        if(trainerBody.getTeam() != null && !trainerBody.getTeam().isEmpty()) {
            List<Pokemon> pokemonList = new ArrayList<>();
            List<String> pokemonIds = trainerBody.getTeam();
            for(String idPokemon: pokemonIds) {
                Pokemon pokemon = pokemonService.findById(idPokemon);
                if(pokemon != null) {
                    pokemonList.add(pokemon);
                }
            }
            pokemonList.addAll(trainer.getTeam());
            trainer.setTeam(pokemonList);
        }
        trainerRepository.save(trainer);
    }

    public void delete(String id){
        trainerRepository.deleteById(id);
    }

}
