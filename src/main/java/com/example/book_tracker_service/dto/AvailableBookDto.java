package com.example.book_tracker_service.dto;

public class AvailableBookDto {

    private Long bookId;

    public AvailableBookDto() {
    }

    public AvailableBookDto(Long id) {
        this.bookId = id;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

}

