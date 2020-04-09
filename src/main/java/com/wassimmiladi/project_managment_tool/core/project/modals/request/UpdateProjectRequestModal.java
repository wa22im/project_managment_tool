package com.wassimmiladi.project_managment_tool.core.project.modals.request;

import com.wassimmiladi.project_managment_tool.core.backlog.entity.Backlog;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UpdateProjectRequestModal {

    String projectName ;


    String discription ;

    Date starDate ;

    Date finishDate ;

    Backlog backlog ;
}
