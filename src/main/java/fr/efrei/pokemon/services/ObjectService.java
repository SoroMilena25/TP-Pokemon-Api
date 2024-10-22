package fr.efrei.pokemon.services;

import fr.efrei.pokemon.dto.CreateObject;
import fr.efrei.pokemon.dto.CreatePokemon;
import fr.efrei.pokemon.dto.UpdateObject;
import fr.efrei.pokemon.dto.UpdatePokemon;
import fr.efrei.pokemon.models.Object;
import fr.efrei.pokemon.models.Pokemon;
import fr.efrei.pokemon.repositories.ObjectRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObjectService {
    private final ObjectRepository objectRepository;

    @Autowired
    public ObjectService(ObjectRepository objectRepository){
        this.objectRepository = objectRepository;
    }

    public List<Object> findAll(){
        return objectRepository.findAll();
    }

    public Object findById(String id){
        return objectRepository.findById(id).orElse(null);
    }

    public void save(CreateObject objectBody){
        Object object = new Object();
        object.setName(objectBody.getName());
        object.setDescription(objectBody.getDescription());
        objectRepository.save(object);
    }

    public void delete(String id){
        objectRepository.deleteById(id);
    }

    @Transactional
    public void update(String id, UpdateObject objectBody) {
        Object object = findById(id);
        if (objectBody.getName() != null) {
            object.setName(objectBody.getName());
        }
        if (objectBody.getDescription() != null) {
            object.setDescription(objectBody.getDescription());
        }
        objectRepository.save(object);
    }
}
