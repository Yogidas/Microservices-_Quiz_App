package com.Microservices.quiz_service.Repository;

import com.Microservices.quiz_service.Entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface quizRepo extends JpaRepository<Quiz,Integer> {
}
