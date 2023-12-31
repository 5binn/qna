package com.sbb.qna.question;

import com.sbb.qna.question.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    Question findBySubject(String subject);
    Question findByContent(String content);
    Question findBySubjectAndContent(String subject, String content);

    List<Question> findBySubjectOrContent(String subject, String content);
    List<Question> findBySubjectLike(String subject);
    Question findBySubjectOrderByCreatedDateAsc(String subject);

}
