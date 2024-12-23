package com.example.book_tracker_service.repository;

import com.example.book_tracker_service.model.BookStatus;
import com.example.book_tracker_service.model.BookStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookStatusRepository extends JpaRepository<BookStatus, Long> {
    List<BookStatus> getByStatusAndDeletedFalse(BookStatusEnum status);

    void deleteByBookId(Long bookId);

    Optional<BookStatus> findByBookIdAndDeletedFalse(Long bookId);
}
