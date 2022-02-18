package com.cleanup.todoc.repository;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.ProjectDao;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

public class ProjectRepositoryImpl implements ProjectRepository {
    public ProjectDao projectDao;
    private LiveData<List<Project>> allProject;

    @Inject
    public ProjectRepositoryImpl(ProjectDao projectDao, LiveData<List<Project>> allProject) {
        this.projectDao = projectDao;
        this.allProject = allProject;
    }

    public LiveData<List<Project>> getAllProjects() {
        return allProject;
    }

    public Project getProjectById(long id) {
        return Objects.requireNonNull(allProject.getValue()).get((int) id);
    }
}
