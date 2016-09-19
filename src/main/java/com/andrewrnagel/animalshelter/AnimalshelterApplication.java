package com.andrewrnagel.animalshelter;

import com.andrewrnagel.animalshelter.repo.AnimalRepo;
import com.andrewrnagel.animalshelter.repo.NoteRepo;
import com.andrewrnagel.animalshelter.repo.TypeRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AnimalshelterApplication {
	@Autowired
	private AnimalRepo animalRepo;
	@Autowired
	private TypeRepo typeRepo;
	@Autowired
	private NoteRepo noteRepo;

	public static void main(String[] args) {
		SpringApplication.run(AnimalshelterApplication.class, args);
	}
}
