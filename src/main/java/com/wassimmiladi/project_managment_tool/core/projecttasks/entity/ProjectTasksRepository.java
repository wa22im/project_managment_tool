package com.wassimmiladi.project_managment_tool.core.projecttasks.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProjectTasksRepository extends JpaRepository<ProjectTask , Long>{

    List<ProjectTask> findByProjectIdentifierOrderByPeriority(String s) ;

    Optional<ProjectTask> findByProjectSequence(String sequence) ;




}
