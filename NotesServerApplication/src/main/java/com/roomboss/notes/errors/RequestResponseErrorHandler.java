package com.roomboss.notes.errors;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class RequestResponseErrorHandler {

    @ExceptionHandler(value = InvalidNoteIDException.class)
    public ResponseEntity<ErrorResponse> handleInvalidIdException(InvalidNoteIDException ex, WebRequest request){
        return ResponseEntity.badRequest().body(
        ErrorResponse.builder().message(String.format("No note found for id: %d, try with correct id",ex.getNoteId())).build());
    }

    @ExceptionHandler(value =   MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handlerInvalidParams(MethodArgumentTypeMismatchException ex, WebRequest request){
        return ResponseEntity.badRequest().body(
                ErrorResponse.builder().message("Invalid id, please try with correct numerical id").build());

    }


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handlerInvalidParams(MethodArgumentNotValidException ex, WebRequest request){
        return ResponseEntity.badRequest().body(
                ErrorResponse.builder().message("validation failed for create post, kindly check title and content field are mandatory").build());

    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> otherExceptions(Exception ex, WebRequest request){
        ex.printStackTrace();
        return ResponseEntity.internalServerError().body(
                ErrorResponse.builder().message("Something went wrong please try later").build());

    }


}
