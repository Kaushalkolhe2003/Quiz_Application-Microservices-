package com.Microservice1.KK.Services;

import java.util.ArrayList;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



import com.Microservice1.KK.Dao.QuizDao;
import com.Microservice1.KK.models.*;
import com.Microservice1.KK.feign.QuizInterface;
@Service
public class QuizServices {
	@Autowired
	QuizDao quizdao;
	
	@Autowired
	QuizInterface quizinterface;

	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

		List<Integer> questions=quizinterface.getQuestionsForQuiz(category, numQ).getBody();
		
		Quiz quiz= new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(questions);
		quizdao.save(quiz);
		
		return new ResponseEntity<>("Success",HttpStatus.OK);
		
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
		Quiz quiz= quizdao.findById(id).get();
		
		List<Integer> question_ids=quiz.getQuestions();
		
		
		ResponseEntity<List<QuestionWrapper>> questions=quizinterface.getQuestionsFromId(question_ids);
		
		return questions;
	}

	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> response) {
		ResponseEntity<Integer> score= quizinterface.getScore(response);
		return score;
		
		 
	}
	
	 
}
