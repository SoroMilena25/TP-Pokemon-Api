package fr.efrei.pokemon.services;

import fr.efrei.pokemon.dto.CreateShop;
import fr.efrei.pokemon.dto.CreateTrainer;
import fr.efrei.pokemon.dto.UpdateShop;
import fr.efrei.pokemon.dto.UpdateTrainer;
import fr.efrei.pokemon.models.Object;
import fr.efrei.pokemon.models.Pokemon;
import fr.efrei.pokemon.models.Shop;
import fr.efrei.pokemon.models.Trainer;
import fr.efrei.pokemon.repositories.ShopRepository;
import fr.efrei.pokemon.repositories.TrainerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopService {

    private final ShopRepository shopRepository;
    private final ObjectService objectService;

    @Autowired
    public ShopService(ShopRepository shopRepository, ObjectService objectService) {
        this.shopRepository = shopRepository;
        this.objectService = objectService;
    }

    public Shop findById(String id){
        return shopRepository.findById(id).orElse(null);
    }

    public List<Shop> findAll(){
        return shopRepository.findAll();
    }

    public void save(CreateShop shopBody){
        Shop shop = new Shop();
        shop.setName(shopBody.getName());

        List<String> objectIds = shopBody.getObjects();

        List<Object> objectAAjouter = new ArrayList<>();

        for(String objectId : objectIds){

            Object object = objectService.findById(objectId);

            if(object != null){
                objectAAjouter.add(object);
            }
        }
        shop.setObjects(objectAAjouter);
        shopRepository.save(shop);
    }

    @Transactional
    public void update(String id, UpdateShop shopBody) {
        Shop shop = findById(id);
        if (shopBody.getName() != null) {
            shop.setName(shopBody.getName());
        }
        if(shopBody.getObjects() != null && !shopBody.getObjects().isEmpty()) {
            List<Object> objectList = new ArrayList<>();
            List<String> objectIds = shopBody.getObjects();
            for(String objectId: objectIds) {
                Object object = objectService.findById(objectId);
                if(object != null) {
                    objectList.add(object);
                }
            }
            objectList.addAll(shop.getObjects());
            shop.setObjects(objectList);
        }
        shopRepository.save(shop);
    }

    public void delete(String id){
        shopRepository.deleteById(id);
    }
}
