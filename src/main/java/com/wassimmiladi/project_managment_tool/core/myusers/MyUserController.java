package com.wassimmiladi.project_managment_tool.core.myusers;


import com.wassimmiladi.project_managment_tool.config.security.JwtTokenProvider;
import com.wassimmiladi.project_managment_tool.core.myusers.entity.MyUser;
import com.wassimmiladi.project_managment_tool.core.myusers.service.UserService;
import com.wassimmiladi.project_managment_tool.payload.JWTLoginSucessResponse;
import com.wassimmiladi.project_managment_tool.payload.LoginRequest;
import com.wassimmiladi.project_managment_tool.utils.DataValidationService;
import com.wassimmiladi.project_managment_tool.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.wassimmiladi.project_managment_tool.config.security.SecurityConstants.TOKEN_PREFIX;

@RestController
@RequestMapping("/api/users")
public class MyUserController {


    @Autowired
    UserService userService ;

    @Autowired
    DataValidationService dataValidationService  ;

    @Autowired
    UserValidator userValidator ;

    @Autowired
    private JwtTokenProvider jwtTokenProvider ;

    @Autowired
    private AuthenticationManager authenticationManager ;
    @PostMapping("/registre")
    public ResponseEntity<?> registreUSer (@Valid @RequestBody MyUser user ,
                                           BindingResult  bindingResult ) {


        userValidator.validate(user , bindingResult );
        // validate password match
        ResponseEntity<?> errorMap = dataValidationService.validateMapService(bindingResult) ;

        if (errorMap!=null) return  errorMap ;

        MyUser newUser = userService.saverUser( user) ;

        return  new ResponseEntity<>( newUser , HttpStatus.CREATED) ;


    }


    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser (@Valid @RequestBody LoginRequest loginRequest ,
                                              BindingResult bindingResult
                                              )
    {
        ResponseEntity<?> errorMap = dataValidationService.validateMapService(bindingResult) ;

        if (errorMap !=null) return errorMap ;
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername() ,
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = TOKEN_PREFIX  + jwtTokenProvider.generateToken(authentication) ;
        return ResponseEntity.ok(new JWTLoginSucessResponse(true , jwt)) ;
    }

}
