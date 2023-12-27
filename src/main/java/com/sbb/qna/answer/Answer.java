package com.sbb.qna.answer;

import com.sbb.qna.question.Question;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Answer {
    @Id //PK 자동추가
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private Integer id;

    @Column(columnDefinition = "TEXT") //TEXT 타입으로 생성
    private String content;

    private LocalDateTime createdDate;

    @ManyToOne
    private Question question;
}
