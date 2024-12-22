package com.example.book_tracker_service.controller;

import com.example.book_tracker_service.dto.AvailableBookDto;
import com.example.book_tracker_service.model.BookStatusEnum;
import com.example.book_tracker_service.service.BookStatusService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book-status")
public class BookStatusController {
    private final BookStatusService bookStatusService;

    public BookStatusController(BookStatusService bookStatusService) {
        this.bookStatusService = bookStatusService;
    }

    @PostMapping("/{id}")
    public void addBookStatus(@PathVariable("id") Long id){
        bookStatusService.addBook(id);
    }

    @DeleteMapping("/{id}")
    public void deleteBookStatus(@PathVariable("id") Long id){
        bookStatusService.deleteBook(id);
    }

    @PatchMapping("/{id}")
    public void updateBookStatus(@PathVariable("id") Long id, @RequestParam("status") BookStatusEnum status){
        bookStatusService.updateBookStatus(id, status);
    }

    @GetMapping()
    public List<AvailableBookDto> getFreeBooks(){
        return bookStatusService.getFreeBooks();
    }


}
