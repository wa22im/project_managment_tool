package com.wassimmiladi.project_managment_tool.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


@Setter
@Getter
@NoArgsConstructor
public class LoginRequest {

    @NotBlank(message =  "username canno be  blank ")
    private  String username ;
    @NotBlank(message =  "pwd canno be  blank ")
    private  String password ;
}
