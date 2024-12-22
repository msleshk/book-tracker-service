package com.example.book_tracker_service.repository;

import com.example.book_tracker_service.model.BookStatus;
import com.example.book_tracker_service.model.BookStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookStatusRepository extends JpaRepository<BookStatus, Long> {
    List<BookStatus> getByStatus(BookStatusEnum status);
}
