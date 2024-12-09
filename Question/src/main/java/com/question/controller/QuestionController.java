package com.question.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.question.model.Question;
import com.question.model.QuestionWrapper;
import com.question.model.Response;
import com.question.service.QuestionService;

@RestController
@RequestMapping("question")
@CrossOrigin("*")
public class QuestionController {

	@Autowired
	QuestionService questionService;

	@PostMapping("create")
	public ResponseEntity<String> addQuestion(@RequestBody Question question) {
		questionService.create(question);
		return new ResponseEntity<>("success", HttpStatus.CREATED);
	}

	@GetMapping("all")
	public ResponseEntity<List<Question>> getAllQuestions() {
		List<Question> questions = questionService.getAll();
		return new ResponseEntity<List<Question>>(questions, HttpStatus.OK);
	}

	@GetMapping("get/{id}")
	public ResponseEntity<Question> getQuestion(@PathVariable int id) {
		Question question = questionService.getById(id);
		return new ResponseEntity<Question>(question, HttpStatus.OK);
	}

	@PutMapping("update")
	public ResponseEntity<Question> updateQuestion(@RequestBody Question question) {
		Question q1 = questionService.modifyQuestion(question);
		return new ResponseEntity<Question>(q1, HttpStatus.OK);
	}

	@GetMapping("category/{category}")
	public ResponseEntity<List<Question>> searchByCategory(@PathVariable String category) {
		List<Question> foundQuestions = questionService.findByCategory(category);
		return new ResponseEntity<>(foundQuestions, HttpStatus.OK);
	}

	@PostMapping("getQuestions")
	public ResponseEntity<List<QuestionWrapper>> getQuestions(@RequestBody List<Integer> questionIds) {
		List<QuestionWrapper> questions = questionService.findQuestions(questionIds);
		return new ResponseEntity<List<QuestionWrapper>>(questions, HttpStatus.OK);
	}

	@PostMapping("getScore")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> response) {
		int score = questionService.calculateScore(response);
		return new ResponseEntity<>(score, HttpStatus.OK);
	}

	@GetMapping("generate")
	public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category,
			@RequestParam int numOfQuestions) {
		List<Integer> questionIds = questionService.generateQuestions(category, numOfQuestions);
		return new ResponseEntity<List<Integer>>(questionIds, HttpStatus.OK);
	}
	
	@DeleteMapping("delete/{id}")
	public void deleteQuestionById(@PathVariable int id)
	{
		questionService.deleteQuestion(id);
		System.out.println("deleted");
		
	}
}
