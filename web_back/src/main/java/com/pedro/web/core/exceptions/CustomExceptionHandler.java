package com.pedro.web.core.exceptions;


import com.pedro.web.core.controller.JsonResponse;
import com.pedro.web.core.controller.JsonResponseFactory;
import com.pedro.web.core.controller.JsonResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class CustomExceptionHandler {

    @Autowired
    protected JsonResponseService jsonResponseService;

    @Autowired
    protected JsonResponseFactory jsonResponseFactory;

    private ResponseEntity<?> formatDefaultResponse(Exception e){
        return this.formatDefaultResponse(e, jsonResponseService.getHttpStatus());
    }
    private ResponseEntity<?> formatDefaultResponse(Exception e, HttpStatus status){
        return this.formatDefaultResponse(e.getMessage(), jsonResponseService.getHttpStatus());
    }


    private ResponseEntity<?> formatDefaultResponse(String messageKey, HttpStatus status){
        jsonResponseService.clearMessages();
        jsonResponseService.addError(messageKey);
        JsonResponse response = jsonResponseFactory.create(null, jsonResponseService.getMessageList());
        return new ResponseEntity<>(response, status);
    }


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(RuntimeException e) {
        System.out.println("------ handleEntityNotFoundException " + e.getStackTrace());
        return this.formatDefaultResponse(ExceptionMessageCode.MENSAGEM_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException e){
        System.out.println("------ handleRuntimeException " + e.getClass() + " / " + e.getMessage());
        return this.formatDefaultResponse(e);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(RuntimeException e){
        System.out.println("------ handleNotFoundException " + e.getClass() + " / " + e.getMessage());
        return this.formatDefaultResponse(e, HttpStatus.FORBIDDEN);
    }


}
