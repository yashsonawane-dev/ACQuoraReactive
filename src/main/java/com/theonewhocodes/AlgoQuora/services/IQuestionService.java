package com.theonewhocodes.AlgoQuora.services;

import com.theonewhocodes.AlgoQuora.dto.PagedQuestionResponseDTO;
import com.theonewhocodes.AlgoQuora.dto.QuestionRequestDTO;
import com.theonewhocodes.AlgoQuora.dto.QuestionResponseDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IQuestionService {

    Mono<QuestionResponseDTO> createQuestion(QuestionRequestDTO questionRequestDTO);

    Mono<QuestionResponseDTO> getQuestionById(String id);

    Flux<QuestionResponseDTO> getAllQuestions();

    Mono<PagedQuestionResponseDTO<QuestionResponseDTO>> searchQuestions(String query, int page, int size);
}
