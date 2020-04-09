package com.wassimmiladi.project_managment_tool.core.projecttasks.entity;


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
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Where(clause = "deleted = false")
@SQLDelete(sql = "Update project_task SET deleted = true Where id = ?")
@Entity
public class ProjectTask extends Audible<String> {

    @Id
    @GeneratedValue (strategy =  GenerationType.IDENTITY)
    Long id ;
    @Column(updatable = false , unique =  true)
    private String projectSequence ;
    @NotBlank(message = " please include a project summary")
    private  String summary ;

    private String acceptanceCriteria ;

    private  String status ;

    private Integer periority ;

    private Date dueDate ;
     @JsonIgnore
    Boolean deleted = false ;


    //  manytoOne with Backlog

    @ManyToOne(fetch =  FetchType.EAGER )
    @JoinColumn(name="backlog_id" , updatable = false , nullable = false )
    @JsonIgnore
    private Backlog backlog  ;
    @Column(updatable =  false)
    private  String projectIdentifier ;


}
