package com.theonewhocodes.AlgoQuora.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewCountEvent {

    private String targetId;
    private String targetType; // e.g., "question", "answer"
    private LocalDateTime timestamp;
}
