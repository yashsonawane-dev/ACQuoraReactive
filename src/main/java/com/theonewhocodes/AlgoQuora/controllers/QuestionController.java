package com.theonewhocodes.AlgoQuora.controllers;

import com.theonewhocodes.AlgoQuora.dto.PagedQuestionResponseDTO;
import com.theonewhocodes.AlgoQuora.dto.QuestionRequestDTO;
import com.theonewhocodes.AlgoQuora.dto.QuestionResponseDTO;
import com.theonewhocodes.AlgoQuora.services.IQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
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

    @GetMapping("/{id}")
    public Mono<QuestionResponseDTO> getQuestionById(@PathVariable String id) {
        return questionService.getQuestionById(id);
    }

    @GetMapping()
    public Flux<QuestionResponseDTO> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/search")
    public Mono<PagedQuestionResponseDTO> searchQuestions(
            @RequestParam String query, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        return questionService.searchQuestions(query, page, size);
    }
}
