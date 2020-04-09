package com.wassimmiladi.project_managment_tool.core.project.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wassimmiladi.project_managment_tool.core.backlog.entity.Backlog;
import com.wassimmiladi.project_managment_tool.utils.Audible;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@Where(clause = "deleted = false")
@SQLDelete(sql = "Update projects_entity SET deleted = true Where id = ?")

public class ProjectsEntity extends Audible<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    Long id ;

    String projectName ;
    @Column(updatable = false , unique = true)
    String projectId ;

    String discription ;
    @JsonFormat(pattern = "yyyy-mm-dd")
    Date starDate ;
    @JsonFormat(pattern = "yyyy-mm-dd")

    Date finishDate ;

    @JsonIgnore
    boolean  deleted ;

    @OneToOne(fetch = FetchType.EAGER  , cascade =  CascadeType.ALL ,mappedBy = "project" )

    private Backlog backlog ;



}
