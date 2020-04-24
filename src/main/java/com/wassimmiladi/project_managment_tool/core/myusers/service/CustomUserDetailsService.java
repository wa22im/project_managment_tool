package com.wassimmiladi.project_managment_tool.core.myusers.service;

import com.wassimmiladi.project_managment_tool.core.myusers.entity.MyUser;
import com.wassimmiladi.project_managment_tool.core.myusers.entity.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class CustomUserDetailsService implements UserDetailsService {


    @Autowired
   private MyUserRepository myUserRepository ;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<MyUser> user =  myUserRepository.findByUsername(username) ;

        if (!user.isPresent()) new UsernameNotFoundException("user name not found") ;

        return  user.get() ;


    }
    @Transactional
    public MyUser loadUserById (Long id ) {
       Optional< MyUser> user = myUserRepository.findById(id ) ;
        if (!user.isPresent()) new UsernameNotFoundException("user name not found") ;

        return  user.get() ;
    }
}
