package com.question.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.question.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
	
	public List<Question>findByCategory(String category);
	
	@Query("select q.id from Question q where q.category =:category ORDER BY RANDOM() LIMIT :numQ")
	public List<Integer> generateQuestions(String category, int numQ);

}
