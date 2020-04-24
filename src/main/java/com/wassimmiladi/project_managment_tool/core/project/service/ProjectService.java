package com.wassimmiladi.project_managment_tool.core.project.service;

import com.wassimmiladi.project_managment_tool.core.backlog.entity.Backlog;
import com.wassimmiladi.project_managment_tool.core.backlog.entity.BacklogRepository;
import com.wassimmiladi.project_managment_tool.core.myusers.entity.MyUser;
import com.wassimmiladi.project_managment_tool.core.myusers.entity.MyUserRepository;
import com.wassimmiladi.project_managment_tool.core.project.entity.ProjectRepo;
import com.wassimmiladi.project_managment_tool.core.project.entity.ProjectsEntity;

import com.wassimmiladi.project_managment_tool.core.project.modals.shared.ProjectDto;
import com.wassimmiladi.project_managment_tool.utils.ErrorMessages;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    @Autowired
    MyUserRepository myUserRepository ;


    @Override
    public ProjectDto findProjectByProjectId(String projectId, String username) {

        projectId =  projectId.toUpperCase() ;
        Optional<ProjectsEntity> myProjectOptional  =projectRepo.findByProjectIdAndCreatedBy( projectId,username)  ;
        if (  !myProjectOptional.isPresent()) throw  new RuntimeException("project  does not Exist") ;

        ProjectDto myReturnedProject = new ProjectDto();

        BeanUtils.copyProperties(myProjectOptional.get(),myReturnedProject);


        return myReturnedProject ;
    }

    @Override
    public ProjectDto createProject(ProjectDto projectDtoReceived , String username)  throws Exception {
        projectDtoReceived.setProjectId(projectDtoReceived.getProjectId().toUpperCase());

        if (projectRepo.findByProjectId(projectDtoReceived.getProjectId()).isPresent()) throw  new RuntimeException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage()) ;

        Backlog newBacklog = new Backlog() ;



        ProjectsEntity entityProjReceived = new ProjectsEntity() ;

        BeanUtils.copyProperties(projectDtoReceived,entityProjReceived);

        entityProjReceived.setBacklog(newBacklog);

        newBacklog.setProject(entityProjReceived);

        newBacklog.setProjectIdentifier(entityProjReceived.getProjectId());

       Optional< MyUser> user = myUserRepository.findByUsername(username) ;

       if (!user.isPresent()) throw   new UsernameNotFoundException("user name error") ;

       entityProjReceived.setUser(user.get());
       ProjectsEntity storedproject  = projectRepo.save(entityProjReceived);

         ProjectDto proReturnedValues = new ProjectDto();

         BeanUtils.copyProperties(storedproject,proReturnedValues);

        return proReturnedValues ;
    }

    @Override
    public  ProjectDto updateProjectByProjectId(String projectId , ProjectDto projectDto,String username) {

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
    public String deleteProjectByProjectId(String projectId , String username) {



        projectId =  projectId.toUpperCase() ;
        System.out.println(projectId);
        Optional<ProjectsEntity> myProjectOptional  =projectRepo.findByProjectIdAndCreatedBy( projectId,username)  ;
        if (  !myProjectOptional.isPresent()) throw  new RuntimeException("project  does not Exist") ;

        projectRepo.deleteById(myProjectOptional.get().getId());

        return " project deleted";
    }

    public List<ProjectDto> findAllProjcts( String username) {

        List<ProjectsEntity> projectsEntities = projectRepo.findAllByCreatedBy(username) ;

        List<ProjectDto> projectDtos = new ArrayList<>();

        for ( ProjectsEntity projEntity : projectsEntities){
            ProjectDto projectDto = new ProjectDto() ;
            BeanUtils.copyProperties(projEntity , projectDto);
            projectDtos.add(projectDto);
        };


        return  projectDtos;
    }
}
