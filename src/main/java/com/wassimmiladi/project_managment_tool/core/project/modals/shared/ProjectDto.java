package com.wassimmiladi.project_managment_tool.core.project.modals.shared;

import com.wassimmiladi.project_managment_tool.core.backlog.entity.Backlog;
import com.wassimmiladi.project_managment_tool.utils.Audible;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
public class ProjectDto  extends Audible<String> implements Serializable {

    Long id ;

    String projectName ;

    String projectId ;

    String discription ;

    Date starDate ;

    Date finishDate ;

    private Backlog backlog ;

}
