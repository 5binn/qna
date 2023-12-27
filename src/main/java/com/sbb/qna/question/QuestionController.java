package com.sbb.qna.question;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;


    @GetMapping("/question/list")
    public String list(Model model) {
        List<Question> questionList = this.questionService.list();
        model.addAttribute("questionList", questionList);
        return "question_list";
    }
}
