package com.Microservices.question_service.Repository;

import com.Microservices.question_service.Entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface questionRepo extends JpaRepository<Questions,Integer> {

     List<Questions> findByCateogory(String cat);
     @Query(value = "select q.id from questions q where q.cateogory=:cateogory ORDER BY RAND() LIMIT :numQ",nativeQuery = true)
     List<Integer> findRandomQuestionsByCateogory(@Param("cateogory") String cateogory, @Param("numQ") int numQ);
}
