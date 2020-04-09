package com.wassimmiladi.project_managment_tool.core.backlog.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wassimmiladi.project_managment_tool.core.project.entity.ProjectsEntity;
import com.wassimmiladi.project_managment_tool.core.projecttasks.entity.ProjectTask;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Where(clause = "deleted = false")
@SQLDelete(sql = "Update backlog SET deleted = true Where id = ?")
public class Backlog  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id ;
    private  Integer ptsequence = 0 ;
    private String projectIdentifier ;
    // OneToOne with  project
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "projectId" , nullable =  false)
    @JsonIgnore
    private ProjectsEntity project ;

    @JsonIgnore
    Boolean deleted = false ;

    // OneToMany with ProjectTask
    @OneToMany (cascade = CascadeType.REFRESH, fetch =  FetchType.EAGER , mappedBy = "backlog" ,orphanRemoval = true)

    private List<ProjectTask> projectTaskList = new ArrayList<>() ;


    @Override
    public String toString() {
        return ""+id +"ptsequence"+ ptsequence + "project" + projectIdentifier ;
    }
}
