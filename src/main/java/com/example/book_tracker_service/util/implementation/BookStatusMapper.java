package com.example.book_tracker_service.util.implementation;

import com.example.book_tracker_service.dto.AvailableBookDto;
import com.example.book_tracker_service.model.BookStatus;
import com.example.book_tracker_service.util.Mapper;
import org.springframework.stereotype.Component;

@Component
public class BookStatusMapper implements Mapper<BookStatus, AvailableBookDto> {
    @Override
    public BookStatus toEntity(AvailableBookDto dto) {
        return null;
    }

    @Override
    public AvailableBookDto toDTO(BookStatus entity) {
        return null;
    }
}
