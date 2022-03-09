package com.cleanup.todoc.repository;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.model.Project;

import java.util.List;

public interface ProjectRepository {
    LiveData<List<Project>> getAllProjects();

    List<Project> getAllProjectsTestUsage();

    void insertProject(Project project);

    void deleteProject(Project project);

}
