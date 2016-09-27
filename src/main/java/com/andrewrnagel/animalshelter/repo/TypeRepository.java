package com.andrewrnagel.animalshelter.repo;

import com.andrewrnagel.animalshelter.entity.Type;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew Nagel and Jimmy Bush on 9/19/16 at 2:08 PM EST.
 */

public interface TypeRepository extends JpaRepository<Type, Integer> {
}
