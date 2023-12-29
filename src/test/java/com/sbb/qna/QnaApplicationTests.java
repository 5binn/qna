package com.sbb.qna;

import com.sbb.qna.answer.Answer;
import com.sbb.qna.answer.AnswerRepository;
import com.sbb.qna.question.Question;
import com.sbb.qna.question.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class QnaApplicationTests {
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    AnswerRepository answerRepository;

    @Test
    void createQ() {
        Question question = new Question();
        question.setContent("sbb가 무엇인가요?");
        question.setSubject("sbb에 대해서 알고 싶습니다.");
        question.setCreatedDate(LocalDateTime.now());
        this.questionRepository.save(question);
    }
    @Test
    void testA01() {
        Optional<Question> optionalQuestion = this.questionRepository.findById(2);
        assertTrue(optionalQuestion.isPresent());
        Question question = optionalQuestion.get();

        Answer answer = new Answer();
        answer.setContent("네 자동으로 생성됩니다.");
        answer.setCreatedDate(LocalDateTime.now());
        answer.setQuestion(question);
        this.answerRepository.save(answer);
    }
    @Test
    void testA02() {
        Optional<Answer> optionalAnswer = this.answerRepository.findById(2);
        assertTrue(optionalAnswer.isPresent());
        Answer answer = optionalAnswer.get();
        assertEquals(2, answer.getQuestion().getId());
    }
    @Test
    void testA03() {
        Optional<Answer> optionalAnswer = this.answerRepository.findById(1);
        assertTrue(optionalAnswer.isPresent());
        Answer answer = optionalAnswer.get();
        answer.setContent("수정했습니당~");
        this.answerRepository.save(answer);
    }
    @Test
    void testA04() {
        assertEquals(1, this.answerRepository.count());
        Optional<Answer> optionalAnswer = this.answerRepository.findById(1);
        assertTrue(optionalAnswer.isPresent());
        Answer answer = optionalAnswer.get();
        this.answerRepository.delete(answer);
        assertEquals(0, answerRepository.count());
    }
    @Test
    void testJpa() {
        Optional<Question> oq = this.questionRepository.findById(1);
        assertTrue(oq.isPresent());
        Question q = oq.get();
        q.setContent("수정된 내용");
        this.questionRepository.save(q);
    }

    @Test
    void test02() {
        List<Question> questionList = this.questionRepository.findAll();
        assertEquals(2, questionList.size());
    }

    @Test
    void test03() {
        Optional<Question> oq = this.questionRepository.findById(1);
        if (oq.isPresent()) {
            Question q = oq.get();
            assertEquals(1,q.getId());
        }
    }

    @Test
    void test04() {
        Question q = this.questionRepository.findBySubject("스프링부트 모델 질문입니다.");
        assertEquals("id는 자동으로 생성되나요?",q.getContent());
    }

    @Test
    void test05() {
        Question q = this.questionRepository.findByContent("id는 자동으로 생성되나요?");
        assertEquals("스프링부트 모델 질문입니다.",q.getSubject());
    }

    @Test
    void test06() {
        Question q = this.questionRepository.findBySubjectAndContent("sbb가 무엇인가요?","sbb에 대해서 알고 싶습니다.");
        assertEquals(1,q.getId());
    }

    @Test
    void test07() {
        List<Question> questionList = this.questionRepository.findBySubjectLike("sbb%");
        assertEquals(1,questionList.size());
    }

    @Test
    void test08() {
        assertEquals(2,this.questionRepository.count());
        Optional<Question> optionalQuestion = this.questionRepository.findById(1);
        assertTrue(optionalQuestion.isPresent());
        Question question = optionalQuestion.get();
        this.questionRepository.delete(question);
        assertEquals(1,this.questionRepository.count());
    }
}
