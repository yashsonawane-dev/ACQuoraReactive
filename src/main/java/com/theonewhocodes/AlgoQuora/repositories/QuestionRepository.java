package com.theonewhocodes.AlgoQuora.repositories;

import com.theonewhocodes.AlgoQuora.models.Question;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface QuestionRepository extends ReactiveMongoRepository<Question, String> {
    Flux<Question> findByTitleContainingIgnoreCase(String query, Pageable pageable);
}
