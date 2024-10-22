package fr.efrei.pokemon.repositories;

import fr.efrei.pokemon.models.Object;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjectRepository extends JpaRepository<Object, String> {
}
