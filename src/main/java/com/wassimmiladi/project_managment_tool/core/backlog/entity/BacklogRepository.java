package com.wassimmiladi.project_managment_tool.core.backlog.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BacklogRepository extends JpaRepository<Backlog,Long> {
    Optional<Backlog> findByProjectIdentifier(String s) ;

}
