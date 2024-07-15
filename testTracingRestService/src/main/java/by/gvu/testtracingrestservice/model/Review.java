package by.gvu.testtracingrestservice.model;

import java.time.LocalDateTime;

public record Review(
        Integer rating,
        String comment,
        LocalDateTime date,
        String reviewerName,
        String reviewerEmail
) {
}
