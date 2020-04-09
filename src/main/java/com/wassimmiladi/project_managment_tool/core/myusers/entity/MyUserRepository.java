package com.wassimmiladi.project_managment_tool.core.myusers.entity;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser,Long> {
}
