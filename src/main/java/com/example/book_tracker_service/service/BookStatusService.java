package com.example.book_tracker_service.service;


import com.example.book_tracker_service.dto.AvailableBookDto;
import com.example.book_tracker_service.model.BookStatusEnum;

import java.util.List;

public interface BookStatusService {

    void addBook(Long bookId);

    List<AvailableBookDto> getFreeBooks();

    void updateBookStatus(Long bookId, BookStatusEnum status);

    void deleteBook(Long bookId);
}
