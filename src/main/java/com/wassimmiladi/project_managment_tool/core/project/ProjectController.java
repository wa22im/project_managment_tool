package com.wassimmiladi.project_managment_tool.core.project;


import com.wassimmiladi.project_managment_tool.core.project.modals.request.CreateProjectUi;
import com.wassimmiladi.project_managment_tool.core.project.modals.request.UpdateProjectRequestModal;
import com.wassimmiladi.project_managment_tool.core.project.modals.response.MessageResponse;
import com.wassimmiladi.project_managment_tool.core.project.modals.response.ResponseCreateProj;
import com.wassimmiladi.project_managment_tool.core.project.modals.response.UpdateResponseModal;
import com.wassimmiladi.project_managment_tool.core.project.modals.shared.ProjectDto;
import com.wassimmiladi.project_managment_tool.core.project.service.ProjectService;
import com.wassimmiladi.project_managment_tool.utils.DataValidationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;


@RestController
@RequestMapping("/api/project**")
@CrossOrigin
public class ProjectController {

    @Autowired
    ProjectService projectService ;

    @Autowired
    DataValidationService dataValidationService  ;


    @GetMapping("/{projectId}")
    ResponseEntity<?> findProjectByProjectId(@PathVariable String projectId,Principal principal){

        ResponseCreateProj returnedProject = new ResponseCreateProj() ;

        ProjectDto projectDtoFounded =   projectService.findProjectByProjectId(projectId,principal.getName()) ;

        BeanUtils.copyProperties(projectDtoFounded,returnedProject);
        return new ResponseEntity<>( returnedProject , HttpStatus.OK)  ;

    }


    @PostMapping("")
    ResponseEntity<?> createNewProject (@Valid @RequestBody CreateProjectUi createProjectUi , BindingResult bindingResult , Principal principal) throws Exception {

        /* form validition*/

        ResponseEntity <?> errorMap = dataValidationService.validateMapService(bindingResult);
        if ( errorMap!=null) return  errorMap ;



        ProjectDto proDetails  = new ProjectDto() ;
        ResponseCreateProj returnedValue = new ResponseCreateProj() ;

        BeanUtils.copyProperties(createProjectUi, proDetails);

        proDetails =   projectService.createProject(proDetails,principal.getName());

        BeanUtils.copyProperties(proDetails,returnedValue);

        return  new ResponseEntity<>(returnedValue , HttpStatus.OK);
    }

    @GetMapping("/all")
    ResponseEntity<?> findAllProjects ( Principal principal) {

        Iterable<ProjectDto> projectDtos = projectService.findAllProjcts (principal.getName() ) ;


        return   new ResponseEntity<>( projectDtos , HttpStatus.OK  ) ;
    }


    @DeleteMapping ("/{projectId}")
    ResponseEntity<?> deleteProjectByProjectId(@PathVariable String projectId, Principal principal){


        MessageResponse messageResponse =new MessageResponse(projectService.deleteProjectByProjectId(projectId ,principal.getName()) ) ;


        return  new ResponseEntity<>(messageResponse , HttpStatus.OK) ;

    }

    @PutMapping("/{projectId}")
    ResponseEntity<?> updateProjectByProjectId(@PathVariable String projectId , @RequestBody UpdateProjectRequestModal updateProjectRequestModal , Principal principal){


        ProjectDto projectDto = new ProjectDto() ;

        BeanUtils.copyProperties(updateProjectRequestModal , projectDto);

        ProjectDto  updatedProjectDto =  projectService.updateProjectByProjectId(projectId , projectDto , principal.getName() ) ;

        UpdateResponseModal updateResponseModal = new UpdateResponseModal() ;

        BeanUtils.copyProperties(updatedProjectDto,updateResponseModal);

        return new ResponseEntity<>(updateProjectRequestModal , HttpStatus.OK) ;

    }
}
