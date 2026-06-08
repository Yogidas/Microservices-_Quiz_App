package QuizApp.Service;

import QuizApp.Entity.Questions;
import QuizApp.Entity.Quiz;
import QuizApp.Entity.Response;
import QuizApp.Repository.questionRepo;
import QuizApp.Repository.quizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    private quizRepo repo;

    @Autowired
    private questionRepo qrepo;

    public ResponseEntity<String> create(String cateogory, int numQ, String title) {
        List<Questions> question=qrepo.findRandomQuestionsByCateogory(cateogory,numQ);

        Quiz q=new Quiz();
        q.setTitle(title);
        q.setQuestions(question);

        repo.save(q);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> response)
    {
        Quiz quiz= repo.findById(id).get();
        List<Questions> question=quiz.getQuestions();
       int i=0,right=0;
        for (Response response1:response)
        {
            System.out.println("Question ID: " + question.get(i).getId());
            System.out.println("Correct Answer: " + question.get(i).getAnswer());
            System.out.println("Response ID: " + response.get(i).getId());
            System.out.println("User Answer: " + response.get(i).getResponse());
             if(response1.getResponse().equals(question.get(i).getAnswer()))
            {
                right++;
            }
            i++;

        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
