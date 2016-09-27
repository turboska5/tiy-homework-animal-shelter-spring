package com.andrewrnagel.animalshelter.repo;

import com.andrewrnagel.animalshelter.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Andrew Nagel and Jimmy Bush on 9/19/16 at 2:08 PM EST.
 */
public interface AnimalRepository extends JpaRepository<Animal, Integer> {

//    Page<Animal> getAllAnimals(Pageable pageable);

    @Query(value = "SELECT * FROM animal WHERE animal_id = ?1 or animal_type_type_id = ?2 or upper(animal_name) LIKE upper(?3)", nativeQuery = true)
        //return list of animals given type (string)
    List<Animal> findByAnimalIDOrAnimalTypeOrNameContaining(Integer animalID, Integer typeID, String name);

    @Query(value = "SELECT * FROM animal WHERE animal_id = ?1 and animal_type_type_id = ?2", nativeQuery = true)
        //return list of animals given type (string)
    List<Animal> findByAnimalIDAndAnimalType(Integer animalID, Integer typeID);

    @Query(value = "SELECT * FROM animal WHERE animal_type_type_id = ?1 and upper(animal_name) LIKE upper(?2)", nativeQuery = true)
        //return list of animals given type (string)
    List<Animal> findByAnimalTypeAndNameContaining(Integer typeID, String name);

    @Query(value = "SELECT * FROM animal WHERE animal_id = ?1 and upper(animal_name) LIKE upper(?2)", nativeQuery = true)
        //return list of animals given type (string)
    List<Animal> findByAnimalIDAndNameContaining(Integer animalID, String name);

    @Query(value = "SELECT * FROM animal WHERE animal_id = ?1 and animal_type_type_id = ?2 and upper(animal_name) LIKE upper(?3)", nativeQuery = true)
        //return list of animals given type (string)
    List<Animal> findByAnimalIDAndAnimalTypeAndNameContaining(Integer animalID, Integer typeID, String name);

}
