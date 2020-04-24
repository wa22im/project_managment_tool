package com.wassimmiladi.project_managment_tool.exceptions.user;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InvalidLoginResponse {
    private String username = "invalid user name" ;
    private String password  = " invalid password ";
}
