package fr.efrei.pokemon.repositories;

import fr.efrei.pokemon.models.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<Shop, String> {
}
