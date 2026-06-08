package com.Microservices.quiz_service.Controller;


import com.Microservices.quiz_service.DTO.QuestionDTO;
import com.Microservices.quiz_service.DTO.QuizDTO;
import com.Microservices.quiz_service.Entity.Response;
import com.Microservices.quiz_service.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;
    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDTO quizDTO)
    {
        return quizService.create(quizDTO.getCateogory(),quizDTO.getNumQuestions(),quizDTO.getTitle());
    }
    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionDTO>> getQuizQuestions(@PathVariable Integer id)
    {
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> response)
    {
        return quizService.calculateResult(id,response);
    }
}

