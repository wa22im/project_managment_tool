package com.wassimmiladi.project_managment_tool.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.NamingStrategy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;


@Getter
@Setter
@NoArgsConstructor
@Service
public class DataValidationService {



    public ResponseEntity <?> validateMapService(BindingResult bindingResult) {

    if ( bindingResult.hasErrors()){
        HashMap<String,String> myErrorMap = new HashMap<>() ;

        for (FieldError error:  bindingResult.getFieldErrors()){
            myErrorMap.put(error.getField(),error.getDefaultMessage());
        }
        return new ResponseEntity<>(myErrorMap  , HttpStatus.BAD_REQUEST);
    }
    else {
       return  null ;
    }
}
}
