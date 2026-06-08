package QuizApp.Repository;

import QuizApp.Entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface questionRepo extends JpaRepository<Questions,Integer> {

     List<Questions> findByCateogory(String cat);
     @Query(value = "select * from questions q where q.cateogory=:cateogory ORDER BY RAND() LIMIT :numQ",nativeQuery = true)
     List<Questions> findRandomQuestionsByCateogory(@Param("cateogory") String cateogory, @Param("numQ") int numQ);
}
