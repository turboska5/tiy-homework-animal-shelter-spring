package com.andrewrnagel.animalshelter.repo;

import com.andrewrnagel.animalshelter.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Andrew Nagel and Jimmy Bush on 9/19/16 at 2:08 PM EST.
 */
public interface NoteRepo extends JpaRepository<Note, Integer> {
}
