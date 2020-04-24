package com.wassimmiladi.project_managment_tool.payload;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class JWTLoginSucessResponse {

    private boolean sucess ;
    private  String token ;


}
