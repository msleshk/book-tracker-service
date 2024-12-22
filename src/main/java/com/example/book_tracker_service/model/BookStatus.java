package com.example.book_tracker_service.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "book_status")
public class BookStatus {
    @Id
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "status")
    private BookStatusEnum status;

    @Column(name = "borrowed_at")
    private LocalDateTime borrowedAt;

    @Column(name = "return_by")
    private LocalDateTime returnBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BookStatusEnum getStatus() {
        return status;
    }

    public void setStatus(BookStatusEnum status) {
        this.status = status;
    }

    public LocalDateTime getBorrowedAt() {
        return borrowedAt;
    }

    public void setBorrowedAt(LocalDateTime borrowedAt) {
        this.borrowedAt = borrowedAt;
    }

    public LocalDateTime getReturnBy() {
        return returnBy;
    }

    public void setReturnBy(LocalDateTime returnBy) {
        this.returnBy = returnBy;
    }
}
