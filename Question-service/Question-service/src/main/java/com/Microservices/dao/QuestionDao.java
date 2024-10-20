package com.Microservices.dao;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Microservices.models.questions;
import org.springframework.data.repository.query.Param;



@Repository
public interface QuestionDao extends JpaRepository<questions, Integer> {
   
	
	
	List<questions> findByCategory(String category);
	
	@Query(value = "SELECT q.id FROM questions q WHERE q.category = :category ORDER BY RAND() LIMIT :numQ", nativeQuery = true)
	List<Integer> findRandomQuestionbyCategory(@Param("category") String category, @Param("numQ") int numQ);

}

