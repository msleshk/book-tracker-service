package com.example.book_tracker_service.dto;

import java.time.LocalDateTime;

public class AvailableBookDto {

    private Long id; // ID книги
    private LocalDateTime returnBy; // Время возврата книги (если нужно)

    public AvailableBookDto(Long id, LocalDateTime returnBy) {
        this.id = id;
        this.returnBy = returnBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getReturnBy() {
        return returnBy;
    }

    public void setReturnBy(LocalDateTime returnBy) {
        this.returnBy = returnBy;
    }
}

