package com.nk.maintenancecard.controller;

import com.nk.maintenancecard.exception.CodeExistedException;
import com.nk.maintenancecard.exception.NotANumberException;
import com.nk.maintenancecard.exception.commonException.NotFoundException;
import com.nk.maintenancecard.exception.customerException.DataTooLongException;
import com.nk.maintenancecard.exception.maintenanceCardException.MoneyExceedException;
import com.nk.maintenancecard.exception.maintenanceCardException.NotEnoughProductException;
import com.nk.maintenancecard.exception.maintenanceCardException.NotFoundRepairmanException;
import com.nk.maintenancecard.exception.maintenanceCardException.NotUpdateException;
import com.nk.maintenancecard.exception.productException.InvalidImageTypeException;
import com.nk.maintenancecard.exception.productException.ProductNotFoundException;
import com.nk.maintenancecard.exception.userException.DuplicateEmailException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler
    private ResponseEntity handleNotFoundException(ProductNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity handleNotANumberException(NotANumberException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity handleCodeExistedException(CodeExistedException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity handleNotEnoughProductException(NotEnoughProductException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    private ResponseEntity handleNotFoundMaintenanceCardException(NotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity handleNotFoundRepairmanException(NotFoundRepairmanException e) {
        if(e.getMessage() == ""){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
        }
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    private ResponseEntity handleEmailDuplicateException(DuplicateEmailException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }
    @ExceptionHandler
    private ResponseEntity handleInvalidImageTypeException(InvalidImageTypeException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity handleMoneyExceedException(MoneyExceedException e) {
        return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity handleNotUpdateException(NotUpdateException e) {
        return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity handleDataTooLongException(DataTooLongException e) {
        return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
    }
}
