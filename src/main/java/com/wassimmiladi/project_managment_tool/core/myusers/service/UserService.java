package com.wassimmiladi.project_managment_tool.core.myusers.service;


import com.wassimmiladi.project_managment_tool.core.myusers.entity.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    @Autowired
    private MyUserRepository myUserRepository ;

    

}
