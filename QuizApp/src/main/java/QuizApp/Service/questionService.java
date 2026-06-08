package QuizApp.Service;

import QuizApp.DTO.QuestionDTO;
import QuizApp.Entity.Questions;
import QuizApp.Entity.Quiz;
import QuizApp.Repository.questionRepo;
import QuizApp.Repository.quizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class questionService {

    @Autowired
    private questionRepo repo;

    @Autowired
    private quizRepo qrepo;

    public String add(Questions questions) {
        repo.save(questions);
        return "Question saved Successfully";
    }


    public List<Questions> getAllQuestion() {
        return repo.findAll();
    }

    public List<Questions> getQuestionByCateogory(String cat) {
        return repo.findByCateogory(cat);
    }

    public ResponseEntity<List<QuestionDTO>> getQuizQuestions(Integer id) {
        Optional<Quiz>quiz= qrepo.findById(id);
        List<Questions> question=quiz.get().getQuestions();
        List<QuestionDTO> questionForUser=new ArrayList<>();
        for (Questions q: question)
        {
            QuestionDTO qw=new QuestionDTO(q.getId(),q.getTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionForUser.add(qw);
        }

        return new ResponseEntity<>(questionForUser, HttpStatus.OK);
    }
}
