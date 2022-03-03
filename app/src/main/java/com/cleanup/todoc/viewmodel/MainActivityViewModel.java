package com.cleanup.todoc.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repository.ProjectRepositoryImpl;
import com.cleanup.todoc.repository.TaskRepositoryImpl;

import java.util.List;
import java.util.Objects;

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

    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }

    public void insertTask(Task task) {
        taskRepositoryImpl.insertTask(task);
    }

    public void deleteTask(Task task) {
        taskRepositoryImpl.deleteTask(task);
    }

    public void setTaskByAlphabeticalOrderASC() {
        allTasks = taskRepositoryImpl.taskByAlphabeticalOrder();
    }

    public void setTaskByAlphabeticalOrderDesc() {
        allTasks = taskRepositoryImpl.taskByAlphabeticalOrder_DESC();
    }

    public void setTaskByCreationOrder() {
        allTasks = taskRepositoryImpl.taskByCreationOrder();
    }

    public void setTaskByCreationOrderDesc() {
        allTasks = taskRepositoryImpl.taskByCreationOrder_DESC();
    }

    public Task getTaskByPosition(int position) {
        return Objects.requireNonNull(allTasks.getValue()).get(position);
    }

}
