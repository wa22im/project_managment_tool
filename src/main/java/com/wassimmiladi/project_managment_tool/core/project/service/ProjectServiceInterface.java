package com.wassimmiladi.project_managment_tool.core.project.service;

import com.wassimmiladi.project_managment_tool.core.project.modals.shared.ProjectDto;

import java.util.List;

public interface ProjectServiceInterface {

    ProjectDto  createProject(ProjectDto projectDtoReceived) throws Exception;

    ProjectDto findProjectByProjectId(String projectId);

    List<ProjectDto> findAllProjcts();

    String  deleteProjectByProjectId(String projectId);

    ProjectDto updateProjectByProjectId(String projectId , ProjectDto projectDto);
}
