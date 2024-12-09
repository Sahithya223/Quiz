package com.quiz.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.feign.QuizInterface;
import com.quiz.model.QuestionWrapper;
import com.quiz.model.Quiz;
import com.quiz.model.QuizDto;
import com.quiz.model.Response;
import com.quiz.repository.QuizRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class QuizService {
	
	@Autowired
	private QuizInterface quizInterface;
	
	@Autowired
	private QuizRepository quizRepository;

	public void create(QuizDto quizDto) {
		// TODO Auto-generated method stub
	List<Integer> qIds=quizInterface.getQuestionsForQuiz(quizDto.getCategory(), quizDto.getNumQ()).getBody();
	Quiz q= new Quiz();
	q.setCategory(quizDto.getCategory());
	q.setTitle(quizDto.getTitle());
	q.setqIds(qIds);
	quizRepository.save(q);
		
	}

	@CircuitBreaker(name="questionService", fallbackMethod="fallbackFindQuestions")
	public List<QuestionWrapper> findQuestions(int quizId) {
		// TODO Auto-generated method stub
	Quiz q= quizRepository.findById(quizId).get();
	List<Integer> qIds=q.getqIds();
	List<QuestionWrapper> qw= quizInterface.getQuestions(qIds).getBody();
		return qw;
	}
	
	public List<QuestionWrapper> fallbackFindQuestions(int quizId)
	{
		return new ArrayList<QuestionWrapper>();
	}

	public int calculateScore(int quizId, List<Response> responses) {
		// TODO Auto-generated method stub
		int score= quizInterface.getScore(responses).getBody();
		return score;
	}
}
