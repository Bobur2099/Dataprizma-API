package com.example.dataprizma.exeption;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings({"unchecked", "rawtypes"})
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        List<String> errorDetails = new ArrayList<>();
        errorDetails.add(request.getDescription(false));
        ErrorResponse errorResponse = new ErrorResponse(new Date(), ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, errorDetails);
        if (ex.getMessage().equals("Access is denied") || ex.getMessage().equals("Доступ запрещен"))
            return new ResponseEntity<>(errorResponse, HttpStatus.EXPECTATION_FAILED);
        else return new ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(RecordNotFoundException ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse(new Date(), ex.getLocalizedMessage(),
                HttpStatus.NOT_FOUND);
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnsupportedMediaType.class)
    public final ResponseEntity<Object> handleUnsupportedMediaType(UnsupportedMediaType ex) {
        ErrorResponse error = new ErrorResponse(new Date(), ex.getLocalizedMessage(),
                HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        return new ResponseEntity(error, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler(ConflictException.class)
    public final ResponseEntity<Object> handleUnsupportedMediaType(ConflictException ex) {
        ErrorResponse error = new ErrorResponse(new Date(), ex.getLocalizedMessage(),
                HttpStatus.CONFLICT);
        return new ResponseEntity(error, HttpStatus.CONFLICT);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        List<String> details = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        ErrorResponse error = new ErrorResponse("Validation Failed", details);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DeleteException.class)
    public final ResponseEntity<Object> deleteExceptions(Exception ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(new Date(), ex.getMessage(), HttpStatus.CONFLICT);
        return new ResponseEntity(errorResponse, HttpStatus.CONFLICT);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        List<String> errorDetails = new ArrayList<>();
        errorDetails.add(request.getDescription(false));
        ErrorResponse errorResponse = new ErrorResponse(new Date(), ex.getMessage(), HttpStatus.BAD_REQUEST, errorDetails);
        if (ex.getMessage().equals("Access is denied") || ex.getMessage().equals("Доступ запрещен"))
            return new ResponseEntity<>(errorResponse, HttpStatus.EXPECTATION_FAILED);
        else return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
    }


}