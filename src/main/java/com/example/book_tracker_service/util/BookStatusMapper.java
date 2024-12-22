package com.example.book_tracker_service.util;

import com.example.book_tracker_service.dto.AvailableBookDto;
import com.example.book_tracker_service.model.BookStatus;
import org.springframework.stereotype.Component;

@Component
public class BookStatusMapper {
    public AvailableBookDto toDTO(BookStatus entity) {
        return new AvailableBookDto(entity.getBookId());
    }
}
