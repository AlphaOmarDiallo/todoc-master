package com.cleanup.todoc.repository;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.ProjectDao;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;

import javax.inject.Inject;

public class ProjectRepositoryImpl implements ProjectRepository {
    public ProjectDao projectDao;
    public ExecutorService executorService;
    public LiveData<List<Project>> allProject;
    public List<Project> listProjects;

    @Inject
    public ProjectRepositoryImpl(ProjectDao projectDao, LiveData<List<Project>> allProject, ExecutorService executorService) {
        this.projectDao = projectDao;
        this.allProject = allProject;
        this.executorService = executorService;

    }

    public LiveData<List<Project>> getAllProjects() {
        return allProject;
    }

    public List<Project> getListProjects() {
        return listProjects = projectDao.getListProjects();
    }

    public Project getProjectById(long id) {
        return Objects.requireNonNull(allProject.getValue()).get((int) id);
    }

    public void insertProject(Project project) {
        executorService.execute(() -> projectDao.insertProject(project));
    }

    public void deleteProject(Project project) {
        executorService.execute(() -> projectDao.deleteProject(project));
    }
}
