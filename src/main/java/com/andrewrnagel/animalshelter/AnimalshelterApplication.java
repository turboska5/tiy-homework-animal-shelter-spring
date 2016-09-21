package com.andrewrnagel.animalshelter;

import com.andrewrnagel.animalshelter.repo.AnimalRepository;
import com.andrewrnagel.animalshelter.repo.NoteRepository;
import com.andrewrnagel.animalshelter.repo.TypeRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AnimalshelterApplication {
	@Autowired
	private AnimalRepository animalRepository;
	@Autowired
	private TypeRepository typeRepository;
	@Autowired
	private NoteRepository noteRepository;

	public static void main(String[] args) {
		SpringApplication.run(AnimalshelterApplication.class, args);
	}
}
