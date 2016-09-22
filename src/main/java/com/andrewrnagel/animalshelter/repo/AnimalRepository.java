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

<<<<<<< HEAD
    @Query(value = "SELECT * FROM animal WHERE animal_id = ?1 or animal_type_type_id = ?2 or animal_name LIKE ?3", nativeQuery = true)
        //return list of animals given type (string)
    List<Animal> findByAnimalIDOrAnimalTypeOrName(Integer animalID, Integer typeID, String name);

    @Query(value = "SELECT * FROM animal WHERE animal_id = ?1 and animal_type_type_id = ?2", nativeQuery = true)
        //return list of animals given type (string)
    List<Animal> findByAnimalIDAndAnimalType(Integer animalID, Integer typeID);

    @Query(value = "SELECT * FROM animal WHERE animal_type_type_id = ?1 and animal_name LIKE ?2", nativeQuery = true)
        //return list of animals given type (string)
    List<Animal> findByAnimalTypeAndName(Integer typeID, String name);

    @Query(value = "SELECT * FROM animal WHERE animal_id = ?1 and animal_name LIKE ?3", nativeQuery = true)
        //return list of animals given type (string)
    List<Animal> findByAnimalIDOAndName(Integer animalID, String name);

    @Query(value = "SELECT * FROM animal WHERE animal_id = ?1 and animal_type_type_id = ?2 and animal_name LIKE ?3", nativeQuery = true)
        //return list of animals given type (string)
    List<Animal> findByAnimalIDAndAnimalTypeAndName(Integer animalID, Integer typeID, String name);


=======
    @Query(value = "SELECT * FROM animals WHERE animal_name = ?1 AND animal_type_type_id = ?2 AND animal_id ?3", nativeQuery = true)
    List<Animal> getAllAnimals(String name, Integer typeID, Integer animalID);
>>>>>>> refs/remotes/origin/master
}
