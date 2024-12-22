package com.example.book_tracker_service.service.implementation;

import com.example.book_tracker_service.dto.AvailableBookDto;
import com.example.book_tracker_service.model.BookStatus;
import com.example.book_tracker_service.model.BookStatusEnum;
import com.example.book_tracker_service.repository.BookStatusRepository;
import com.example.book_tracker_service.service.BookStatusService;
import com.example.book_tracker_service.util.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class BookStatusServiceImpl implements BookStatusService {

    private final BookStatusRepository bookStatusRepository;
    private final Mapper<BookStatus, AvailableBookDto> mapper;

    public BookStatusServiceImpl(BookStatusRepository bookStatusRepository, Mapper<BookStatus, AvailableBookDto> mapper) {
        this.bookStatusRepository = bookStatusRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public void addBook(Long id) {
        BookStatus bookStatus = new BookStatus();
        bookStatus.setId(id);
        bookStatus.setStatus(BookStatusEnum.AVAILABLE);
        bookStatusRepository.save(bookStatus);
    }

    @Override
    public List<AvailableBookDto> getFreeBooks() {
        return bookStatusRepository.getByStatus(BookStatusEnum.AVAILABLE).stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateBookStatus(Long id, BookStatusEnum status) {
        BookStatus bookStatus = bookStatusRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException()); //TODO change to custom exc
        bookStatus.setStatus(status);
        bookStatusRepository.save(bookStatus); // TODO add logic for different statuses
    }

    @Override
    @Transactional
    public void deleteBook(Long id) {
        bookStatusRepository.deleteById(id);
    }

}
