package com.wassimmiladi.project_managment_tool.core.myusers.service;


import com.wassimmiladi.project_managment_tool.core.myusers.entity.MyUser;
import com.wassimmiladi.project_managment_tool.core.myusers.entity.MyUserRepository;
import com.wassimmiladi.project_managment_tool.exceptions.user.exception.UsernameAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    @Autowired
    private MyUserRepository myUserRepository ;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder ;


    public  MyUser saverUser (MyUser newUser ) {
        try {

            newUser.setUsername(newUser.getUsername() );



            //user name has to be unique

            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));




            // we don't persist or show confirmpassword
            newUser.setConfirmPassword("");
            return myUserRepository.save(newUser);
        }catch (Exception ex){
            throw new UsernameAlreadyExistsException("Username "+newUser.getUsername()+" already exists") ;
        }

    }



}
