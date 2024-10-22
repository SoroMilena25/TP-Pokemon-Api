package fr.efrei.pokemon.controller;

import fr.efrei.pokemon.dto.CreateShop;
import fr.efrei.pokemon.dto.CreateTrainer;
import fr.efrei.pokemon.dto.UpdateShop;
import fr.efrei.pokemon.dto.UpdateTrainer;
import fr.efrei.pokemon.models.Shop;
import fr.efrei.pokemon.models.Trainer;
import fr.efrei.pokemon.services.ShopService;
import fr.efrei.pokemon.services.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shops")
public class ShopController {

    private final ShopService shopService;

    @Autowired
    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping
    public ResponseEntity<List<Shop>> findAll(){
        return new ResponseEntity<>(shopService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Shop> findById(@PathVariable String id){
        Shop shop = shopService.findById(id);
        if(shop == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(shop, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Shop> create(@RequestBody CreateShop shop){
        shopService.save(shop);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody UpdateShop shopBody) {
        Shop shop = shopService.findById(id);
        if (shop == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        shopService.update(id, shopBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        Shop shop = shopService.findById(id);
        if(shop == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        shopService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
