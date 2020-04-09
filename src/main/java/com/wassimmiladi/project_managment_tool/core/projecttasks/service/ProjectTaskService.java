package com.wassimmiladi.project_managment_tool.core.projecttasks.service;


import com.wassimmiladi.project_managment_tool.core.backlog.entity.Backlog;
import com.wassimmiladi.project_managment_tool.core.backlog.entity.BacklogRepository;
import com.wassimmiladi.project_managment_tool.core.projecttasks.entity.ProjectTask;
import com.wassimmiladi.project_managment_tool.core.projecttasks.entity.ProjectTasksRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.*;

@Service
@Getter
@Setter
public class ProjectTaskService {


    @Autowired
    private BacklogRepository   backlogRepository ;

    @Autowired
    private ProjectTasksRepository projectTasksRepository ;

    public  ProjectTask  addProjectTask (String projectIdentifier  , ProjectTask projectTask ) {


        Optional<Backlog> backlogOptional = backlogRepository.findByProjectIdentifier(projectIdentifier);

        if (  !backlogOptional.isPresent() ) throw new RuntimeException("backlog does not exist") ;

        Backlog backlogUpdated = backlogOptional.get() ;

        projectTask.setBacklog(backlogUpdated);

        Integer backlogSequence = backlogUpdated.getPtsequence() ;

        backlogSequence ++  ;

        projectTask.setProjectSequence(projectIdentifier +"+"+backlogSequence);

        backlogUpdated.setPtsequence(backlogSequence);

        if ( projectTask.getPeriority()== null || projectTask.getPeriority() ==0  ){

            projectTask.setPeriority(3);

        }
        if ( projectTask.getStatus()==null || projectTask.getStatus().equals("")){
            projectTask.setStatus("To - Do");
        }
        projectTask.setProjectIdentifier(backlogUpdated.getProjectIdentifier());

        backlogRepository.save(backlogUpdated) ;
        return projectTasksRepository.save(projectTask)  ;

    }

    public List<ProjectTask> findBacklogById(String backlog_id) {

        if ( backlogRepository.findByProjectIdentifier(backlog_id) ==null ) throw new RuntimeException(" backlog doesn't exist") ;

        ArrayList<ProjectTask> projectTasks= (ArrayList<ProjectTask>) projectTasksRepository.findByProjectIdentifierOrderByPeriority(backlog_id);


        return  projectTasks  ;

    }

    public ProjectTask findProjectTaskBySequence ( String backlog_id ,String sequence   ) {

      Optional<ProjectTask> foundedProjectTask = projectTasksRepository.findByProjectSequence(sequence) ;

      Optional<Backlog> backlog = backlogRepository.findByProjectIdentifier(backlog_id) ;
      if ( !backlog.isPresent()) throw new RuntimeException("backlog doesnot exist ") ;
      if (  !foundedProjectTask.isPresent()) throw  new RuntimeException("project task does not exist")  ;

      ProjectTask projectTaskReturned = foundedProjectTask.get();

      Backlog foundeBacklog = backlog.get() ;

      if (  ! projectTaskReturned.getProjectIdentifier().equals(foundeBacklog.getProjectIdentifier())) throw  new RuntimeException("not matchin") ;



      return  projectTaskReturned  ;

    }

    public  ProjectTask   updateProjectTask ( ProjectTask projectTask , String backlog_id  , String sequence ){

        Optional<ProjectTask> foundedProjectTask = projectTasksRepository.findByProjectSequence(sequence) ;

        Optional<Backlog> backlog = backlogRepository.findByProjectIdentifier(backlog_id) ;
        if ( !backlog.isPresent()) throw new RuntimeException("backlog doesnot exist ") ;
        if (  !foundedProjectTask.isPresent()) throw  new RuntimeException("project task does not exist")  ;


        ProjectTask projectTaskReturned = foundedProjectTask.get();

        Backlog foundeBacklog = backlog.get() ;



        if (  ! projectTaskReturned.getProjectIdentifier().equals(foundeBacklog.getProjectIdentifier())) throw  new RuntimeException("not matchin") ;
        BeanUtils.copyProperties(projectTask, projectTaskReturned, getNullPropertyNames(projectTask));

        projectTasksRepository.save(projectTaskReturned) ;

        return  projectTaskReturned  ;

    }


    public static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }


    public List<ProjectTask> deleteProjectTask(String backlog_id, String projecttaskid) {


        Optional<ProjectTask> foundedProjectTask = projectTasksRepository.findByProjectSequence(projecttaskid) ;

        Optional<Backlog> backlog = backlogRepository.findByProjectIdentifier(backlog_id) ;
        if ( !backlog.isPresent()) throw new RuntimeException("backlog doesnot exist ") ;
        if (  !foundedProjectTask.isPresent()) throw  new RuntimeException("project task does not exist")  ;

        ProjectTask projectTaskReturned = foundedProjectTask.get();

        Backlog foundeBacklog = backlog.get() ;

        if (  ! projectTaskReturned.getProjectIdentifier().equals(foundeBacklog.getProjectIdentifier())) throw  new RuntimeException("not matchin") ;



           Long idProjectTask = projectTaskReturned.getId() ;

           projectTasksRepository.deleteById(idProjectTask);

        ArrayList<ProjectTask> projectTasks= (ArrayList<ProjectTask>) projectTasksRepository.findByProjectIdentifierOrderByPeriority(backlog_id);

        return  projectTasks  ;


    }
}
