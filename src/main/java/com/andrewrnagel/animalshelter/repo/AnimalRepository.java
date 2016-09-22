package com.andrewrnagel.animalshelter.repo;

import com.andrewrnagel.animalshelter.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Andrew Nagel and Jimmy Bush on 9/19/16 at 2:08 PM EST.
 */
public interface AnimalRepository extends JpaRepository<Animal, Integer> {

//    @Query(value = "SELECT * FROM animals WHERE animal_ID = ?1", nativeQuery = true)

    //return list of animals given type (string)
    //List<Animal> findByType(int typeID);
}
