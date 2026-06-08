package com.Microservices.quiz_service.Service;


import com.Microservices.quiz_service.DTO.QuestionDTO;
import com.Microservices.quiz_service.Entity.Questions;
import com.Microservices.quiz_service.Entity.Quiz;
import com.Microservices.quiz_service.Entity.Response;
import com.Microservices.quiz_service.Feign.QuizInterface;
import com.Microservices.quiz_service.Repository.quizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private quizRepo repo;

    @Autowired
    private QuizInterface quizInterface;

    public ResponseEntity<String> create(String cateogory, int numQ, String title) {

        List<Integer> questions=quizInterface.getQuestionForQuiz(cateogory,numQ).getBody();
        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionid(questions);
        repo.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> response)
    {
        ResponseEntity<Integer> score=quizInterface.getScore(response);
        return score;
    }

    public ResponseEntity<List<QuestionDTO>> getQuizQuestions(Integer id) {
            Quiz quiz= repo.findById(id).get();
            List<Integer> questionIds=quiz.getQuestionid();
            ResponseEntity<List<QuestionDTO>> questions=quizInterface.getQuestionsFromId(questionIds);
            return questions;
    }
}
