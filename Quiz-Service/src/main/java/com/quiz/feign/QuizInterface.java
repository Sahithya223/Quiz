package com.quiz.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.quiz.model.QuestionWrapper;
import com.quiz.model.Response;

//as we are not using api-gateway, user will send request to quiz-controller there it goes to quiz-controller to quiz service.In this case we need to mention question-microservice name as a value to feign client.
//we have implemented api-gateway, user will send request to api-gateway from there it goes to quiz-controller. as quiz controller wants to communicate with question service, we send back that request to api-gateway from there it goes to question controller. In this case we need to pass the value as gateway service name to feign client.
//By using the api-gateway service name we can communicate to any Microservice as those are registered with api-gateway.
@FeignClient("question-service")
public interface QuizInterface {

	@GetMapping("question/generate")
	public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category, @RequestParam int numOfQuestions);
	
	@PostMapping("question/getScore")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> response);
	
	@PostMapping("question/getQuestions")
	public ResponseEntity<List<QuestionWrapper>> getQuestions(@RequestBody List<Integer> questionIds);
}
