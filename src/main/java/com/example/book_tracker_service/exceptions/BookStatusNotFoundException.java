package com.example.book_tracker_service.exceptions;

public class BookStatusNotFoundException extends RuntimeException{
    public BookStatusNotFoundException(String message){
        super(message);
    }
}
