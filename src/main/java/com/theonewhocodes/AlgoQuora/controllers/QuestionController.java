package com.theonewhocodes.AlgoQuora.controllers;

import com.theonewhocodes.AlgoQuora.dto.QuestionRequestDTO;
import com.theonewhocodes.AlgoQuora.dto.QuestionResponseDTO;
import com.theonewhocodes.AlgoQuora.services.IQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequestMapping("/api/questions")
@RestController
@RequiredArgsConstructor
public class QuestionController {

    private final IQuestionService questionService;

    @PostMapping
    public Mono<QuestionResponseDTO> createQuestion(@RequestBody QuestionRequestDTO questionRequestDTO) {
        return questionService.createQuestion(questionRequestDTO);
    }
}
