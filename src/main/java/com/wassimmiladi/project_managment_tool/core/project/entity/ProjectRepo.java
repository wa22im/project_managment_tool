package com.wassimmiladi.project_managment_tool.core.project.entity;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;



public interface ProjectRepo extends JpaRepository<ProjectsEntity,Long> {

    Optional<ProjectsEntity> findByProjectId(String id);
    Optional<ProjectsEntity> findByProjectIdAndCreatedBy(String id,String username);


    List<ProjectsEntity> findAllByCreatedBy(String username ) ;
}
