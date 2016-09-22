package com.andrewrnagel.animalshelter.repo;

import com.andrewrnagel.animalshelter.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created by Andrew Nagel and Jimmy Bush on 9/19/16 at 2:08 PM EST.
 */
public interface AnimalRepository extends JpaRepository<Animal, Integer> {

    @Query(value = "SELECT * FROM animals WHERE animal_name = ?1 AND animal_type_type_id = ?2 AND animal_id ?3", nativeQuery = true)
    List<Animal> getAllAnimals(String name, Integer typeID, Integer animalID);
}
