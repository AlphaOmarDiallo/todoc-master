package com.cleanup.todoc.repository;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.ProjectDao;

import java.util.List;
import java.util.concurrent.ExecutorService;

import javax.inject.Inject;

public class ProjectRepositoryImpl implements ProjectRepository {
    public ProjectDao projectDao;
    public ExecutorService executorService;
    public LiveData<List<Project>> allProject;

    @Inject
    public ProjectRepositoryImpl(ProjectDao projectDao, LiveData<List<Project>> allProject, ExecutorService executorService) {
        this.projectDao = projectDao;
        this.allProject = allProject;
        this.executorService = executorService;
    }

    public LiveData<List<Project>> getAllProjects() {
        return allProject;
    }

    @Override
    public List<Project> getAllProjectsTestUsage() {
        return projectDao.getAllProjectsTestUsage();
    }

    public void insertProject(Project project) {
        executorService.execute(() -> projectDao.insertProject(project));
    }

    public void deleteProject(Project project) {
        executorService.execute(() -> projectDao.deleteProject(project));
    }
}
