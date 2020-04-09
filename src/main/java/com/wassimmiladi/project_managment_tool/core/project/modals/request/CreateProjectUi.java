package com.wassimmiladi.project_managment_tool.core.project.modals.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class CreateProjectUi {

    @NotBlank(message = "please entre  a name")
    String projectName ;
    @NotBlank(message = "project id is required ")
    @Size(min=5,max =15 , message = " a valid project id must have from 5 to 10 chars")
    String projectId ;
    @NotBlank(message = "discription is required ")
    String discription ;
     Date starDate ;
     Date finishDate;
}
