package com.Microservice1.KK.Dao;

import org.springframework.data.jpa.repository.JpaRepository;


import com.Microservice1.KK.models.*;
public interface QuizDao extends JpaRepository<Quiz,Integer>{
	
}
