package com.wassimmiladi.project_managment_tool.core.myusers.entity;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser,Long> {

    Optional<MyUser> findByUsername (String  username ) ;

    Optional<MyUser> findById(Long id ) ;


}
