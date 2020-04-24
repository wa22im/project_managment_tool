package com.wassimmiladi.project_managment_tool.core.project.service;

import com.wassimmiladi.project_managment_tool.core.project.modals.shared.ProjectDto;

import java.util.List;

public interface ProjectServiceInterface {

    ProjectDto  createProject(ProjectDto projectDtoReceived , String username) throws Exception;

    ProjectDto findProjectByProjectId(String projectId, String username);

    List<ProjectDto> findAllProjcts( String username);

    String  deleteProjectByProjectId(String projectId, String username);

    ProjectDto updateProjectByProjectId(String projectId , ProjectDto projectDto,String username);
}
