package com.Microservices.question_service.Service;

import com.Microservices.question_service.DTO.QuestionDTO;
import com.Microservices.question_service.Entity.Questions;
import com.Microservices.question_service.Entity.Response;
import com.Microservices.question_service.Repository.questionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class questionService {

    @Autowired
    private questionRepo repo;


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

    public ResponseEntity<List<Integer>> getQuestionForQuiz(String cateogoryName, Integer questionNo) {
        List<Integer> questions=repo.findRandomQuestionsByCateogory(cateogoryName,questionNo);
        return new ResponseEntity<>(questions,HttpStatus.OK);
    }


    public ResponseEntity<List<QuestionDTO>> getQuestionFromId(List<Integer> questionsId) {

        List<QuestionDTO> wrappers=new ArrayList<>();
        List<Questions> question=new ArrayList<>();

        for(Integer id:questionsId)
        {
            question.add(repo.findById(id).get());
        }

        for(Questions q:question)
        {
            QuestionDTO ques=new QuestionDTO();
            ques.setId(q.getId());
            ques.setTitle(q.getTitle());
            ques.setOption1(q.getOption1());
            ques.setOption2(q.getOption2());
            ques.setOption3(q.getOption3());
            ques.setOption4(q.getOption4());
            wrappers.add(ques);
        }
        return new ResponseEntity<>(wrappers,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {


        int right=0;
        for (Response response1:responses)
        {
            Questions question=repo.findById(response1.getId()).get();

            if(response1.getResponse().equals(question.getAnswer()))
            {
                right++;
            }

        }
        return new ResponseEntity<>(right,HttpStatus.OK);

    }
}
