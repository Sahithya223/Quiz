package com.question.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.question.model.Question;
import com.question.model.QuestionWrapper;
import com.question.model.Response;
import com.question.repository.QuestionRepository;

@Service
public class QuestionService {

	Logger logger = LoggerFactory.getLogger(QuestionService.class);
	@Autowired
	QuestionRepository questionRepository;

	public void create(Question question) {
		questionRepository.save(question);
	}

	public List<Question> getAll() {
		// TODO Auto-generated method stub
		return questionRepository.findAll();
	}

	public Question getById(int id){
		// TODO Auto-generated method stub
		Optional<Question> question=questionRepository.findById(id);
		if(question.isEmpty()) {
			return null;
		}
		else
		  return question.get();
	}

	public Question modifyQuestion(Question question) {
		// TODO Auto-generated method stub
		Optional<Question> q= questionRepository.findById(question.getId());
		if(q.isPresent())
		{
			questionRepository.save(question);
			return question;
		}
		else
			return null;
		
	}

	public List<Question> findByCategory(String category) {
		// TODO Auto-generated method stub
		return questionRepository.findByCategory(category);
	}

	public List<QuestionWrapper> findQuestions(List<Integer> questionIds) {
		// TODO Auto-generated method stub
		List<QuestionWrapper> questions= new ArrayList<>();
		for(Integer i: questionIds)
		{
			Question q= questionRepository.findById(i).get();
			QuestionWrapper qw= new QuestionWrapper();
			qw.setQuestionId(q.getId());
			qw.setQuestionTitle(q.getQuestionTitle());
			qw.setOption1(q.getOption1());
			qw.setOption2(q.getOption2());
			qw.setOption3(q.getOption3());
			qw.setOption4(q.getOption4());
			questions.add(qw);
		}
		logger.info("Inside getById ");
		return questions;
	}

	public int calculateScore(List<Response> response) {
		// TODO Auto-generated method stub
		int score=0;
		 for(Response r: response)
		 {
			 Question q= questionRepository.findById(r.getQuestionId()).get();
			 if(q.getCorrectAnswer().equals(r.getResponse()))
			 {
				 score++;
			 }
		 }
		return score;
	}

	public List<Integer> generateQuestions(String category, int numOfQuestions) {
		// TODO Auto-generated method stub
		List<Integer> qIds=questionRepository.generateQuestions(category, numOfQuestions);
		return qIds;
	}
	public void deleteQuestion(int id)
	{
		questionRepository.deleteById(id);
	}
}
