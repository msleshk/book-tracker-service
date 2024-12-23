package com.example.book_tracker_service.service.implementation;

import com.example.book_tracker_service.dto.AvailableBookDto;
import com.example.book_tracker_service.exceptions.BookStatusNotFoundException;
import com.example.book_tracker_service.model.BookStatus;
import com.example.book_tracker_service.model.BookStatusEnum;
import com.example.book_tracker_service.repository.BookStatusRepository;
import com.example.book_tracker_service.service.BookStatusService;
import com.example.book_tracker_service.util.BookStatusMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class BookStatusServiceImpl implements BookStatusService {

    private final BookStatusRepository bookStatusRepository;
    private final BookStatusMapper mapper;

    public BookStatusServiceImpl(BookStatusRepository bookStatusRepository, BookStatusMapper mapper) {
        this.bookStatusRepository = bookStatusRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public void addBook(Long bookId) {
        BookStatus bookStatus = new BookStatus();
        bookStatus.setBookId(bookId);
        bookStatus.setStatus(BookStatusEnum.AVAILABLE);
        bookStatusRepository.save(bookStatus);
    }

    @Override
    public List<AvailableBookDto> getFreeBooks() {
        return bookStatusRepository.getByStatusAndDeletedFalse(BookStatusEnum.AVAILABLE).stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateBookStatus(Long bookId, BookStatusEnum status) {
        BookStatus bookStatus = bookStatusRepository.findByBookIdAndDeletedFalse(bookId)
                .orElseThrow(() -> new BookStatusNotFoundException("No such book status!")); //TODO change to custom exc
        bookStatus.setStatus(status);
        if (status == BookStatusEnum.BORROWED) {
            LocalDateTime now = LocalDateTime.now();
            bookStatus.setBorrowedAt(now);
            bookStatus.setReturnBy(now.plusDays(7));
        } else if (status == BookStatusEnum.AVAILABLE) {
            bookStatus.setBorrowedAt(null);
            bookStatus.setReturnBy(null);
        }

        bookStatusRepository.save(bookStatus);
    }

    @Override
    @Transactional
    public void deleteBook(Long bookId) {
        BookStatus bookStatus = bookStatusRepository.findByBookIdAndDeletedFalse(bookId)
                .orElseThrow(() -> new BookStatusNotFoundException("No such book status!"));
        bookStatus.setDeleted(true);
        bookStatusRepository.save(bookStatus);
    }

}
