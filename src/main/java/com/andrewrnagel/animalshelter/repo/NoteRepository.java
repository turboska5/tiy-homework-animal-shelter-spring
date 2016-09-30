package com.andrewrnagel.animalshelter.repo;

import com.andrewrnagel.animalshelter.entity.Animal;
import com.andrewrnagel.animalshelter.entity.Note;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Andrew Nagel and Jimmy Bush on 9/19/16 at 2:08 PM EST.
 */

public interface NoteRepository extends JpaRepository<Note, Integer> {

    @Query(value = "SELECT * FROM note WHERE animal_id = ?1 ORDER BY created DESC", nativeQuery = true)
    List<Note> findByAnimalOrderByDate(Integer animalID);

//    List<Note> findByAnimalOrderByNoteCreationDateDesc(Integer animalID);
}