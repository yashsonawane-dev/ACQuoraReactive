package com.theonewhocodes.AlgoQuora.services;

import com.theonewhocodes.AlgoQuora.dto.QuestionRequestDTO;
import com.theonewhocodes.AlgoQuora.dto.QuestionResponseDTO;
import com.theonewhocodes.AlgoQuora.mapper.QuestionMapper;
import com.theonewhocodes.AlgoQuora.models.Question;
import com.theonewhocodes.AlgoQuora.repositories.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements IQuestionService{

    private final QuestionRepository questionRepository;

    @Override
    public Mono<QuestionResponseDTO> createQuestion(QuestionRequestDTO questionRequestDTO) {

        Question question = Question.builder()
                .title(questionRequestDTO.getTitle())
                .content(questionRequestDTO.getContent())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return questionRepository.save(question)
                .map(QuestionMapper::toResponseDTO)
                .doOnSuccess(savedQuestion -> System.out.println("Question created: " + savedQuestion.getTitle()))
                .doOnError(error -> System.err.println("Error creating question: " + error.getMessage()));
    }
}
