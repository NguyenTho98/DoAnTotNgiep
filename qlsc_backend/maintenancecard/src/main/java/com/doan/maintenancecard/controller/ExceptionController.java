package com.doan.maintenancecard.controller;

import com.doan.maintenancecard.exception.CodeExistedException;
import com.doan.maintenancecard.exception.NotANumberException;
import com.doan.maintenancecard.exception.commonException.NotFoundException;
import com.doan.maintenancecard.exception.customerException.DataTooLongException;
import com.doan.maintenancecard.exception.maintenanceCardException.MoneyExceedException;
import com.doan.maintenancecard.exception.maintenanceCardException.NotEnoughProductException;
import com.doan.maintenancecard.exception.maintenanceCardException.NotFoundRepairmanException;
import com.doan.maintenancecard.exception.maintenanceCardException.NotUpdateException;
import com.doan.maintenancecard.exception.productException.InvalidImageTypeException;
import com.doan.maintenancecard.exception.productException.ProductNotFoundException;
import com.doan.maintenancecard.exception.userException.DuplicateEmailException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler
    private ResponseEntity<?> handleNotFoundException(ProductNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<?> handleNotANumberException(NotANumberException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<?> handleCodeExistedException(CodeExistedException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<?> handleNotEnoughProductException(NotEnoughProductException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    private ResponseEntity<?> handleNotFoundMaintenanceCardException(NotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<?> handleNotFoundRepairmanException(NotFoundRepairmanException e) {
        if (e.getMessage().equals("")) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
        }
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<?> handleEmailDuplicateException(DuplicateEmailException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler
    private ResponseEntity<?> handleInvalidImageTypeException(InvalidImageTypeException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<?> handleMoneyExceedException(MoneyExceedException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<?> handleNotUpdateException(NotUpdateException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<?> handleDataTooLongException(DataTooLongException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
