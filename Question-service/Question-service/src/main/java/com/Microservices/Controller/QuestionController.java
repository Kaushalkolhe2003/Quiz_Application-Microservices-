package com.Microservices.Controller;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Microservices.Service.QuestionServices;
import com.Microservices.models.*;
import com.Microservices.models.*;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("question")
public class QuestionController {
	@Autowired
	QuestionServices questionServices;
	@GetMapping("/allquestions")
	public ResponseEntity<List<questions>> getAllQuestions() {
		return questionServices.getAllQuestions();
	}
	@GetMapping("category/{cat}")
	public ResponseEntity<List<questions>> getQuestionbyCategory(@PathVariable("cat") String name){
		return questionServices.getQuestionbycategory(name);
	}
	@PostMapping("add")
	public ResponseEntity<String> addQuestion(@RequestBody questions question) {
		 return questionServices.addQuestion(question);
		
	}
    @DeleteMapping("delete/{id}")
    public String deleteQuestion(@PathVariable("id") int id) {
        return questionServices.deleteQuestion(id);
    }
    
    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName,@RequestParam int  numQuestions ){
    	return questionServices.getQuestionForQuiz(categoryName,numQuestions);
    	
    }
    
    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds){
    	return questionServices.getQuestionFromId(questionIds);
    }
    
    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses ){
    	return questionServices.getScore(responses);
    }
    
    
    //generate 
    //get question{question id}
    //get score
    

}



