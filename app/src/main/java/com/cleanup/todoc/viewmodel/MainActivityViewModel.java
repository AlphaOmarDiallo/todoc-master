package com.cleanup.todoc.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repository.ProjectRepositoryImpl;
import com.cleanup.todoc.repository.TaskRepositoryImpl;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MainActivityViewModel extends ViewModel {

    private final TaskRepositoryImpl taskRepositoryImpl;
    private final ProjectRepositoryImpl projectRepositoryImpl;
    LiveData<List<Project>> allProjects;
    LiveData<List<Task>> allTasks;

    @Inject
    public MainActivityViewModel(TaskRepositoryImpl taskRepositoryImpl, ProjectRepositoryImpl projectRepositoryImpl) {
        this.taskRepositoryImpl = taskRepositoryImpl;
        this.projectRepositoryImpl = projectRepositoryImpl;
        allTasks = taskRepositoryImpl.getAllTasks();
        allProjects = projectRepositoryImpl.getAllProjects();
    }

    public LiveData<List<Project>> getAllProjects() {
        return allProjects;
    }

    public List<Project> listProjects() {
        return projectRepositoryImpl.getListProjects();
    }

    public Project getProjectById(long id) {
        return projectRepositoryImpl.getProjectById(id);
    }

    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }

    public void insertTask(Task task) {
        taskRepositoryImpl.insertTask(task);
    }

    public void deleteTask(Task task) {
        taskRepositoryImpl.deleteTask(task);
    }

}
