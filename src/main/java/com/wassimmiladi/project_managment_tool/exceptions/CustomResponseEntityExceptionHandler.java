package com.wassimmiladi.project_managment_tool.exceptions;


import com.wassimmiladi.project_managment_tool.exceptions.user.exception.UsernameAlreadyExistsException;
import com.wassimmiladi.project_managment_tool.exceptions.user.responses.UsernameAlreadyExists;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler {




    @ExceptionHandler
    public  final ResponseEntity<?> handleUsernameAlreadyExists (UsernameAlreadyExistsException ex , WebRequest request){

        UsernameAlreadyExists usernameAlreadyExists = new UsernameAlreadyExists(ex.getMessage()) ;
        return  new ResponseEntity<>(usernameAlreadyExists , HttpStatus.BAD_REQUEST) ;
    }

}
