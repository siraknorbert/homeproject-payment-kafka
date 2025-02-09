package hu.siraknorbert.homeproject.controller;

import hu.siraknorbert.homeproject.dto.ErrorResponseDto;
import hu.siraknorbert.homeproject.enumeration.ErrorCodeEnum;
import hu.siraknorbert.homeproject.exception.checked.InvalidInputException;
import hu.siraknorbert.homeproject.exception.checked.MissingInputException;
import hu.siraknorbert.homeproject.exception.checked.NotFoundException;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@Hidden
@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(InvalidInputException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponseDto> handleInvalidInputException(InvalidInputException ex) {
        return createErrorResponse(ex.getErrorCodeEnum(), ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingInputException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponseDto> handleMissingInputException(MissingInputException ex) {
        return createErrorResponse(ex.getErrorCodeEnum(), ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponseDto> handleConstraintViolationException(ConstraintViolationException ex) {
        List<String> errors = new ArrayList<>();
        for (ConstraintViolation violation : ex.getConstraintViolations()) {
            errors.add(violation.getPropertyPath().toString() + ": " + violation.getMessage());
        }
        return createErrorResponse(ErrorCodeEnum.INVALID_INPUT, errors.toString(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponseDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        return createErrorResponse(ErrorCodeEnum.INVALID_INPUT, errors.toString(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponseDto> handleNotFoundExceptionException(NotFoundException ex) {
        return createErrorResponse(ex.getErrorCodeEnum(), ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponseDto> handleException(Exception ex) {
        String errorMessage = "Unexpected error occurred!";
        return createErrorResponse(ErrorCodeEnum.UNEXPECTED_ERROR, errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private static ResponseEntity<ErrorResponseDto> createErrorResponse(ErrorCodeEnum errorCode, String errorMessage, HttpStatus httpStatus) {
        ErrorResponseDto errorResponseDto = convertToErrorResponseDto(errorCode, errorMessage);
        return ResponseEntity.status(httpStatus).body(errorResponseDto);
    }

    private static ErrorResponseDto convertToErrorResponseDto(ErrorCodeEnum errorCode, String errorMessage) {
        ErrorResponseDto dto = new ErrorResponseDto();
        dto.setErrorCode(errorCode);
        dto.setErrorMessage(errorMessage);
        return dto;
    }
}
