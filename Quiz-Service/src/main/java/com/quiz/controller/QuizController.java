package com.quiz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.model.QuestionWrapper;
import com.quiz.model.QuizDto;
import com.quiz.model.Response;
import com.quiz.service.QuizService;

@RestController
@RequestMapping("quiz")
public class QuizController {
	
	@Autowired
	private QuizService quizService;
	
	@PostMapping("create")
	public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto)
	{
		quizService.create(quizDto);
		return new ResponseEntity<String>("created", HttpStatus.CREATED);
	}
	
	@GetMapping("get/{quizId}")
	public ResponseEntity<List<QuestionWrapper>> getQuestions(@PathVariable int quizId){
		
		List<QuestionWrapper> qw= quizService.findQuestions(quizId);
		return new ResponseEntity<List<QuestionWrapper>>(qw, HttpStatus.OK);
		
	}
	
	@PostMapping("submit/{quizId}")
	public ResponseEntity<Integer> getScore(@PathVariable int quizId, @RequestBody List<Response> responses)
	{
		int score= quizService.calculateScore(quizId,responses);
		return new ResponseEntity<Integer>(score, HttpStatus.OK);
	}
	

}
