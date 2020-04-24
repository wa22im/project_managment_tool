package com.wassimmiladi.project_managment_tool.validator;


import com.wassimmiladi.project_managment_tool.core.myusers.entity.MyUser;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return MyUser.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
    MyUser myUser = (MyUser) object ;
    if (myUser.getPassword().length()<6){
        errors.rejectValue("password" , "length ","Password must be at least 6 characters");
    }
    if ( !myUser.getPassword().equals(myUser.getConfirmPassword())){
        errors.rejectValue("password" , "match" , "passwords must match");
    }
    }
}
