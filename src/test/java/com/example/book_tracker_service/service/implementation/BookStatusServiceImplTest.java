package com.example.book_tracker_service.service.implementation;

import com.example.book_tracker_service.dto.AvailableBookDto;
import com.example.book_tracker_service.exceptions.BookStatusNotFoundException;
import com.example.book_tracker_service.model.BookStatus;
import com.example.book_tracker_service.model.BookStatusEnum;
import com.example.book_tracker_service.repository.BookStatusRepository;
import com.example.book_tracker_service.util.BookStatusMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookStatusServiceImplTest {

    @Mock
    private BookStatusRepository bookStatusRepository;

    @Mock
    private BookStatusMapper mapper;

    @InjectMocks
    private BookStatusServiceImpl bookStatusService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addBook_shouldSaveBookStatus() {
        Long bookId = 1L;

        bookStatusService.addBook(bookId);

        verify(bookStatusRepository, times(1)).save(any(BookStatus.class));
    }

    @Test
    void getFreeBooks_shouldReturnAvailableBooks() {
        BookStatus bookStatus = new BookStatus();
        bookStatus.setBookId(1L);
        bookStatus.setStatus(BookStatusEnum.AVAILABLE);

        AvailableBookDto dto = new AvailableBookDto();
        dto.setBookId(1L);

        when(bookStatusRepository.getByStatusAndDeletedFalse(BookStatusEnum.AVAILABLE))
                .thenReturn(Collections.singletonList(bookStatus));
        when(mapper.toDTO(bookStatus)).thenReturn(dto);

        List<AvailableBookDto> freeBooks = bookStatusService.getFreeBooks();

        assertNotNull(freeBooks);
        assertEquals(1, freeBooks.size());
        assertEquals(1L, freeBooks.get(0).getBookId());
    }

    @Test
    void updateBookStatus_shouldUpdateToBorrowed() {
        Long bookId = 1L;
        BookStatus bookStatus = new BookStatus();
        bookStatus.setBookId(bookId);
        bookStatus.setStatus(BookStatusEnum.AVAILABLE);

        when(bookStatusRepository.findByBookIdAndDeletedFalse(bookId)).thenReturn(Optional.of(bookStatus));

        bookStatusService.updateBookStatus(bookId, BookStatusEnum.BORROWED);

        assertEquals(BookStatusEnum.BORROWED, bookStatus.getStatus());
        assertNotNull(bookStatus.getBorrowedAt());
        assertNotNull(bookStatus.getReturnBy());
        verify(bookStatusRepository, times(1)).save(bookStatus);
    }

    @Test
    void updateBookStatus_shouldThrowExceptionIfNotFound() {
        Long bookId = 1L;
        when(bookStatusRepository.findByBookIdAndDeletedFalse(bookId)).thenReturn(Optional.empty());

        assertThrows(BookStatusNotFoundException.class, () -> bookStatusService.updateBookStatus(bookId, BookStatusEnum.BORROWED));
    }

    @Test
    void deleteBook_shouldMarkAsDeleted() {
        Long bookId = 1L;
        BookStatus bookStatus = new BookStatus();
        bookStatus.setBookId(bookId);

        when(bookStatusRepository.findByBookIdAndDeletedFalse(bookId)).thenReturn(Optional.of(bookStatus));

        bookStatusService.deleteBook(bookId);

        assertTrue(bookStatus.isDeleted());
        verify(bookStatusRepository, times(1)).save(bookStatus);
    }

    @Test
    void deleteBook_shouldThrowExceptionIfNotFound() {
        Long bookId = 1L;
        when(bookStatusRepository.findByBookIdAndDeletedFalse(bookId)).thenReturn(Optional.empty());

        assertThrows(BookStatusNotFoundException.class, () -> bookStatusService.deleteBook(bookId));
    }
}
