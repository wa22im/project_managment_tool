package com.wassimmiladi.project_managment_tool.core.myusers.entity;


import com.wassimmiladi.project_managment_tool.utils.Audible;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class MyUser {

    @Id
    @GeneratedValue (strategy =  GenerationType.IDENTITY)
    private  Long id ;

    @Email (message ="Username needs to be an email")
    @NotBlank(message = "Username is required")
    @Column(unique = true)
    private  String username ;
    @NotBlank(message = "Please entre your full name" )
    private  String fullName ;
    @NotBlank(message =  "Password field is required")
    private  String password ;

    //oneToMany projects
    @Transient
    private  String confirmPassword ;

    private Date created_at ;

    private Date updated_at ;

    @PrePersist
    protected  void onCreate(){this.created_at = new Date() ; }

    @PreUpdate
    protected  void onUpdate(){this.updated_at = new Date() ; }


}
