package com.sbb.qna.question;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Question {
    @Id //PK 자동추가
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private Integer id;
    @Column(length = 200)
    private String subject;
    @Column(columnDefinition = "TEXT") //TEXT 타입으로 생성
    private String content;
    private LocalDateTime createdDate;

//    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
//    private List<Answer> answerList;

}
