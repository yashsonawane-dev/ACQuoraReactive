package com.theonewhocodes.AlgoQuora.services;

import com.theonewhocodes.AlgoQuora.dto.QuestionRequestDTO;
import com.theonewhocodes.AlgoQuora.dto.QuestionResponseDTO;
import com.theonewhocodes.AlgoQuora.exceptions.QuestionNotFoundException;
import com.theonewhocodes.AlgoQuora.mapper.QuestionMapper;
import com.theonewhocodes.AlgoQuora.models.Question;
import com.theonewhocodes.AlgoQuora.repositories.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements IQuestionService {

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

    @Override
    public Mono<QuestionResponseDTO> getQuestionById(String id) {
        return questionRepository.findById(id)
                .map(QuestionMapper::toResponseDTO)
                .doOnSuccess(question -> {
                    if (question != null) {
                        System.out.println("Retrieved question: " + question.getTitle());
                    }
                })
                .onErrorResume(error -> {
                    System.err.println("Error retrieving question: " + error.getMessage());
                    return Mono.error(new RuntimeException("An unexpected error occurred while retrieving the question"));
                })
                .switchIfEmpty(Mono.error(new QuestionNotFoundException("Question not found with id: " + id)));
    }

    @Override
    public Flux<QuestionResponseDTO> getAllQuestions() {
        return questionRepository.findAll()
                .map(QuestionMapper::toResponseDTO)
                .doOnComplete(() -> System.out.println("All questions retrieved successfully"))
                .doOnError(error -> Flux.error(new RuntimeException("An unexpected error occurred while retrieving all questions: " + error.getMessage())))
                .switchIfEmpty(Flux.error(new QuestionNotFoundException("No questions found")));
    }

    @Override
    public Flux<QuestionResponseDTO> searchQuestions(String query, int page, int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        return questionRepository.findByTitleContainingIgnoreCase(query, pageable)
                .map(QuestionMapper::toResponseDTO)
                .doOnComplete(() -> System.out.println("Search completed for query: " + query))
                .doOnError(error -> Flux.error(new RuntimeException("An unexpected error occurred while searching questions: " + error.getMessage())))
                .switchIfEmpty(Flux.error(new QuestionNotFoundException("No questions found for query: " + query)));
    }
}
