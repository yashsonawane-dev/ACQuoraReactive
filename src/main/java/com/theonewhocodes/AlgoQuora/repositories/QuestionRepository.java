package com.theonewhocodes.AlgoQuora.repositories;

import com.theonewhocodes.AlgoQuora.models.Question;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends ReactiveMongoRepository<Question, String> {
}
