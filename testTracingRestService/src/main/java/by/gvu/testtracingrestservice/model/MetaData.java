package by.gvu.testtracingrestservice.model;

import java.time.LocalDateTime;

public record MetaData(
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String barcode,
        String qrCode
) {
}
