package com.Microservices.Service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Microservices.dao.QuestionDao;
import com.Microservices.models.QuestionWrapper;
import com.Microservices.models.Response;
import com.Microservices.models.questions;



@Service
public class QuestionServices {
	@Autowired
	QuestionDao questiondao;
	
	public ResponseEntity<List<questions>>getAllQuestions() {
		try {
			return new  ResponseEntity<>( questiondao.findAll(),HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	  return new  ResponseEntity<>( new ArrayList<>(),HttpStatus.BAD_REQUEST);
		
	}

	public ResponseEntity<List<questions>> getQuestionbycategory(String name) {
		try {
			return new ResponseEntity<>( questiondao.findByCategory(name),HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
		
	}

	public ResponseEntity<String> addQuestion(questions question) {
		
		questiondao.save(question);
		
		return new ResponseEntity<>("Added Successfully",HttpStatus.CREATED);		
	}

	public String deleteQuestion(int id) {
	    questiondao.deleteById(id);
	    return "Question with ID " + id + " deleted successfully.";
	}

	public ResponseEntity<List<Integer>> getQuestionForQuiz(String categoryName, Integer numQuestions) {
		List<Integer> questions=questiondao.findRandomQuestionbyCategory(categoryName, numQuestions);
		return new ResponseEntity<>(questions,HttpStatus.OK);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuestionFromId(List<Integer> questionIds) {
		List<QuestionWrapper> wrappers = new ArrayList<>();
		List<questions> questions= new ArrayList<>();
		
		for(Integer id: questionIds) {
			questions.add(questiondao.findById(id).get());
			
		}
		
		for(questions question : questions) {
			QuestionWrapper wrapper = new QuestionWrapper();
			wrapper.setId(question.getId());
			wrapper.setQuestion_title(question.getQuestion_title());
			wrapper.setOption1(question.getOption1());
			wrapper.setOption2(question.getOption2());
			wrapper.setOption3(question.getOption3());
			wrapper.setOption4(question.getOption4());
			wrappers.add(wrapper);
		}
		return new ResponseEntity<>(wrappers,HttpStatus.OK);
	}

	public ResponseEntity<Integer> getScore(List<Response> responses) {
		 int right=0;
		 for(Response response :responses) {
			 questions question = questiondao.findById(response.getId()).get();
			 
			 if(response.getResponse().equals(question.getRight_answer())) {
				 right++;
			 }
		 }
		 return new ResponseEntity<>(right,HttpStatus.OK);
	}
	  
}
