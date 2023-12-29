package com.sbb.qna.question;


import com.sbb.qna.answer.AnswerForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;
@RequestMapping("/question")
@Controller
@RequiredArgsConstructor
@ToString
public class QuestionController {
    private final QuestionService questionService;


    @GetMapping("/list")
    public String list(Model model) {
        List<Question> questionList = this.questionService.list();
        model.addAttribute("questionList", questionList);
        return "question_list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer id, Model model, AnswerForm answerForm) {
        Question question = this.questionService.getQuestion(id);

        model.addAttribute("question",question);
        return "question_detail";
    }

    @GetMapping("/create")
    public String create(QuestionForm questionForm) {
        return "question_create";
    }
    @PostMapping("/create")
    public String create(@Valid QuestionForm questionForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "question_create";
        }
        this.questionService.create(questionForm.getSubject(), questionForm.getContent());
        return  "redirect:/";
    }

}
