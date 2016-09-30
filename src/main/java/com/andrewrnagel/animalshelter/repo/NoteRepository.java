package com.andrewrnagel.animalshelter.repo;

import com.andrewrnagel.animalshelter.entity.Note;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Created by Andrew Nagel and Jimmy Bush on 9/19/16 at 2:08 PM EST.
 */

public interface NoteRepository extends JpaRepository<Note, Integer> {

//    List<Note> findByAnimalId(Integer animalID, Sort sort);
}