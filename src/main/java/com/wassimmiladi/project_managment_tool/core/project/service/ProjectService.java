package com.wassimmiladi.project_managment_tool.core.project.service;

import com.wassimmiladi.project_managment_tool.core.backlog.entity.Backlog;
import com.wassimmiladi.project_managment_tool.core.backlog.entity.BacklogRepository;
import com.wassimmiladi.project_managment_tool.core.project.entity.ProjectRepo;
import com.wassimmiladi.project_managment_tool.core.project.entity.ProjectsEntity;

import com.wassimmiladi.project_managment_tool.core.project.modals.shared.ProjectDto;
import com.wassimmiladi.project_managment_tool.utils.ErrorMessages;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService implements  ProjectServiceInterface {

    @Autowired
    ProjectRepo projectRepo ;
    @Autowired
    BacklogRepository backlogRepository ;


    @Override
    public ProjectDto findProjectByProjectId(String projectId) {

        projectId =  projectId.toUpperCase() ;
        Optional<ProjectsEntity> myProjectOptional  =projectRepo.findByProjectId( projectId)  ;
        if (  !myProjectOptional.isPresent()) throw  new RuntimeException("project  does not Exist") ;

        ProjectDto myReturnedProject = new ProjectDto();

        BeanUtils.copyProperties(myProjectOptional.get(),myReturnedProject);


        return myReturnedProject ;
    }

    @Override
    public ProjectDto createProject(ProjectDto projectDtoReceived)  throws Exception {
        projectDtoReceived.setProjectId(projectDtoReceived.getProjectId().toUpperCase());

        if (projectRepo.findByProjectId(projectDtoReceived.getProjectId()).isPresent()) throw  new RuntimeException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage()) ;

        Backlog newBacklog = new Backlog() ;



        ProjectsEntity entityProjReceived = new ProjectsEntity() ;

        BeanUtils.copyProperties(projectDtoReceived,entityProjReceived);

        entityProjReceived.setBacklog(newBacklog);

        newBacklog.setProject(entityProjReceived);

        newBacklog.setProjectIdentifier(entityProjReceived.getProjectId());

         ProjectsEntity storedproject  = projectRepo.save(entityProjReceived);

         ProjectDto proReturnedValues = new ProjectDto();

         BeanUtils.copyProperties(storedproject,proReturnedValues);

        return proReturnedValues ;
    }

    @Override
    public  ProjectDto updateProjectByProjectId(String projectId , ProjectDto projectDto) {

        projectId =  projectId.toUpperCase() ;
        System.out.println(projectId);
        Optional<ProjectsEntity> myProjectOptional  =projectRepo.findByProjectId( projectId)  ;
        if (  !myProjectOptional.isPresent()) throw  new RuntimeException("project  does not Exist") ;

        ProjectsEntity updatedProj = myProjectOptional.get() ;


        if ( projectDto.getProjectName()!=null) updatedProj.setProjectName(projectDto.getProjectName());
        if ( projectDto.getDiscription()!=null) updatedProj.setDiscription(projectDto.getDiscription());
        if ( projectDto.getStarDate()!=null) updatedProj.setStarDate(projectDto.getStarDate());
        if ( projectDto.getFinishDate()!=null) updatedProj.setFinishDate(projectDto.getFinishDate());
        if ( projectDto.getBacklog()!=null) {


         Optional<Backlog> oldbacklog  = backlogRepository.findByProjectIdentifier(projectId.toUpperCase()) ;

         if ( !oldbacklog.isPresent()) throw  new RuntimeException(" probleme backlog n'est pas trouve") ;

         Backlog newBacklog = oldbacklog.get();

         newBacklog.setPtsequence(projectDto.getBacklog().getPtsequence());

         backlogRepository.save(newBacklog);




        }





        projectRepo.save(updatedProj);

        ProjectDto updatedProjDto = new ProjectDto()   ;

        BeanUtils.copyProperties(updatedProj , updatedProjDto);


        return updatedProjDto ;
    }

    @Override
    public String deleteProjectByProjectId(String projectId) {



        projectId =  projectId.toUpperCase() ;
        System.out.println(projectId);
        Optional<ProjectsEntity> myProjectOptional  =projectRepo.findByProjectId( projectId)  ;
        if (  !myProjectOptional.isPresent()) throw  new RuntimeException("project  does not Exist") ;

        projectRepo.deleteById(myProjectOptional.get().getId());

        return " project deleted";
    }

    public List<ProjectDto> findAllProjcts() {

        List<ProjectsEntity> projectsEntities = projectRepo.findAll();;

        List<ProjectDto> projectDtos = new ArrayList<>();

        for ( ProjectsEntity projEntity : projectsEntities){
            ProjectDto projectDto = new ProjectDto() ;
            BeanUtils.copyProperties(projEntity , projectDto);
            projectDtos.add(projectDto);
        };


        return  projectDtos;
    }
}
