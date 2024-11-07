package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.*;
import java.util.*;

@Repository
public interface RecipeDAO extends JpaRepository<RecipeEntity, Integer>{
	@Query(value = "SELECT * FROM recipe ORDER BY hit DESC "
			+ "LIMIT 0, 5", nativeQuery = true)
	public List<RecipeEntity> recipeTopData();
}
