package com.example.book_tracker_service.controller;

import com.example.book_tracker_service.dto.AvailableBookDto;
import com.example.book_tracker_service.model.BookStatusEnum;
import com.example.book_tracker_service.service.BookStatusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Add a new book status",
            description = "Adds a new book status. This operation is handled internally by the book storage service.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Book status added successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid book ID or invalid or missing bearer token")
    })
    public void addBookStatus(@PathVariable("id") Long id) {
        bookStatusService.addBook(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete book status",
            description = "Deletes the status of a book when the book is removed from the book storage service. This operation is handled internally.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Book status deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Book status not found or invalid or missing bearer token")
    })
    public void deleteBookStatus(@PathVariable("id") Long id) {
        bookStatusService.deleteBook(id);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update book status",
            description = "Updates the status of a book by its ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Book status updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid status or book ID")
    })
    public String updateBookStatus(@PathVariable("id") Long id, @RequestParam("status") BookStatusEnum status) {
        bookStatusService.updateBookStatus(id, status);
        return "Book status updated!";
    }

    @GetMapping()
    @Operation(summary = "Get all available books",
            description = "Retrieves a list of all books that are currently available for borrowing.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of available books",
                    content = @Content(schema = @Schema(implementation = AvailableBookDto.class)))
    })
    public List<AvailableBookDto> getFreeBooks() {
        return bookStatusService.getFreeBooks();
    }


}
