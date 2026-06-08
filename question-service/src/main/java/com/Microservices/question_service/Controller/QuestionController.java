package com.Microservices.question_service.Controller;
import com.Microservices.question_service.DTO.QuestionDTO;
import com.Microservices.question_service.Entity.Questions;
import com.Microservices.question_service.Entity.Response;
import com.Microservices.question_service.Service.questionService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("question")
public class QuestionController {

    private static final Logger log = LoggerFactory.getLogger(QuestionController.class);
    @Autowired
    private questionService questionService;

    @Autowired
    Environment environment;

    @GetMapping("/allQuestions")
    public List<Questions> getAllQuestion()
    {
    return questionService.getAllQuestion();
    }

    @GetMapping("/cateogory/{cat}")
    public ResponseEntity<List<Questions>> getQuestionByCateogory(@PathVariable String cat)
    {
        try {
            return new ResponseEntity<>(questionService.getQuestionByCateogory(cat), HttpStatus.OK);
        } catch (Exception e) {
            log.error("This is throwing error",e);
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }

    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody Questions questions)
    {
        try{
            questionService.add(questions);
            return new ResponseEntity<>("Successfull",HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("you get the error",e);
        }
         return new ResponseEntity<>("Failed to save",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("generate")
    public ResponseEntity<List<Integer>>getQuestionForQuiz(@RequestParam String cateogoryName,@RequestParam Integer questionNo)
    {
        return questionService.getQuestionForQuiz(cateogoryName,questionNo);
    }


    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionDTO>> getQuestionsFromId(@RequestBody List<Integer> questionsId)
    {
        System.out.println(environment.getProperty("local.server.port"));
        return questionService.getQuestionFromId(questionsId);
    }

    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses)
    {
        return questionService.getScore(responses);
    }

}
