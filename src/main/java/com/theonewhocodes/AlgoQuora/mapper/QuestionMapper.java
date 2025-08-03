package com.theonewhocodes.AlgoQuora.mapper;

import com.theonewhocodes.AlgoQuora.dto.QuestionResponseDTO;
import com.theonewhocodes.AlgoQuora.models.Question;

public class QuestionMapper {

    public static QuestionResponseDTO toResponseDTO(Question question) {
        if (question == null) {
            return null;
        }

        return QuestionResponseDTO.builder()
                .id(question.getId())
                .title(question.getTitle())
                .content(question.getContent())
                .createdAt(question.getCreatedAt())
                .build();
    }
}
