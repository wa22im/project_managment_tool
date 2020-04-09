package com.wassimmiladi.project_managment_tool.core.project.modals.response;

import com.wassimmiladi.project_managment_tool.core.backlog.entity.Backlog;
import com.wassimmiladi.project_managment_tool.utils.Audible;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
public class ResponseCreateProj  extends Audible<String> {


    String projectName ;

    String projectId ;

    String discription ;

    Date starDate ;

    Date finishDate ;



}
