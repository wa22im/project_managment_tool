package com.wassimmiladi.project_managment_tool.core.backlog.controller;


import com.wassimmiladi.project_managment_tool.core.projecttasks.entity.ProjectTask;
import com.wassimmiladi.project_managment_tool.core.projecttasks.entity.ProjectTasksRepository;
import com.wassimmiladi.project_managment_tool.core.projecttasks.service.ProjectTaskService;
import com.wassimmiladi.project_managment_tool.utils.DataValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/backlog")
@CrossOrigin
public class BacklogController
{
    @Autowired
    private ProjectTaskService projectTaskService;

    @Autowired
    private DataValidationService dataValidationService ;

    @PostMapping("/{backlog_id}")
    public ResponseEntity<?>  AddProjectTaskToBacklog(@Valid @RequestBody ProjectTask projectTask
                                                      , BindingResult result , @PathVariable String backlog_id ){



        ResponseEntity<?> errorMap = dataValidationService.validateMapService(result);

        if ( errorMap != null) return  errorMap ;

        ProjectTask projectTask1 = projectTaskService.addProjectTask(backlog_id , projectTask) ;



        return  new ResponseEntity<>(projectTask , HttpStatus.CREATED) ;
    }

    @GetMapping("/{backlog_id}")
    public ResponseEntity < ?> getProjectBacklogById (@PathVariable String backlog_id){



        return  new ResponseEntity<>(projectTaskService.findBacklogById(backlog_id) , HttpStatus.OK) ;
    }

    @GetMapping("/{backlog_id}/{projecttaskid}")

    public  ResponseEntity<?>  getPrjectTask(  @PathVariable String backlog_id  , @PathVariable String projecttaskid)
    {
        ProjectTask projectTaskFounded = projectTaskService.findProjectTaskBySequence(backlog_id,projecttaskid) ;

        return  new ResponseEntity<>(projectTaskFounded , HttpStatus.OK);
    }


    @PutMapping("/{backlog_id}/{projecttaskid}")
    public  ResponseEntity<?> updateProjectTask (@Valid @RequestBody ProjectTask projectTask ,
                                                 BindingResult bindingResult ,
                                                 @PathVariable String backlog_id ,
                                                 @PathVariable String projecttaskid) {


        ResponseEntity<?> errorMap = dataValidationService.validateMapService(bindingResult) ;


        ProjectTask projectTask1 =         projectTaskService.updateProjectTask (  projectTask ,  backlog_id  ,  projecttaskid ) ;

        return  new ResponseEntity<>( projectTask1 , HttpStatus.OK ) ;
    }


    @DeleteMapping("/{backlog_id}/{projecttaskid}")
    public  ResponseEntity<?>  deleteProjectTask (

                                                 @PathVariable String backlog_id ,
                                                 @PathVariable String projecttaskid
                                                     ) {




      //  ProjectTask projectTask1 = projectTaskService.deleteProjectTask (     backlog_id  ,  projecttaskid ) ;

        return  new ResponseEntity<>( projectTaskService.deleteProjectTask (     backlog_id  ,  projecttaskid )  , HttpStatus.OK ) ;
    }


}
