package QuizApp.Controller;
import QuizApp.DTO.QuestionDTO;
import QuizApp.Entity.Questions;
import QuizApp.Entity.Response;
import QuizApp.Service.QuizService;
import QuizApp.Service.questionService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    private QuizService quizService;

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

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionDTO>> getQuizQuestions(@PathVariable Integer id)
    {
        return questionService.getQuizQuestions(id);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> response)
    {
            return quizService.calculateResult(id,response);
    }
}
