package com.example.book_tracker_service.service;


import com.example.book_tracker_service.dto.AvailableBookDto;
import com.example.book_tracker_service.model.BookStatusEnum;

import java.util.List;

public interface BookStatusService {

    void addBook(Long id);

    List<AvailableBookDto> getFreeBooks();

    void updateBookStatus(Long id, BookStatusEnum status);

    void deleteBook(Long id);
}
