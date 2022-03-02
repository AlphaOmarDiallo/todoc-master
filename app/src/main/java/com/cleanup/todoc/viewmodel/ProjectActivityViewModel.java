package com.cleanup.todoc.viewmodel;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.ColorInt;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repository.ProjectRepositoryImpl;
import com.cleanup.todoc.repository.TaskRepositoryImpl;
import com.cleanup.todoc.utils.RandomColorUtil;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ProjectActivityViewModel extends ViewModel {

    private final TaskRepositoryImpl taskRepositoryImpl;
    private final ProjectRepositoryImpl projectRepositoryImpl;
    LiveData<List<Project>> allProjects;
    LiveData<List<Task>> allTasks;

    @Inject
    public ProjectActivityViewModel(TaskRepositoryImpl taskRepositoryImpl, ProjectRepositoryImpl projectRepositoryImpl) {
        this.taskRepositoryImpl = taskRepositoryImpl;
        this.projectRepositoryImpl = projectRepositoryImpl;
        allTasks = taskRepositoryImpl.getAllTasks();
        allProjects = projectRepositoryImpl.getAllProjects();
    }

    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }

    public LiveData<List<Project>> getAllProjects() {
        return allProjects;
    }

    public void insertProject(Project project) {
        projectRepositoryImpl.insertProject(project);
        Log.e(TAG, "insertProject: new project added", null);
    }

    public void deleteProject(Project project) {
        projectRepositoryImpl.deleteProject(project);
    }

    public List<Project> currentList(List<Project> projects) {
        return projects;
    }

    public @ColorInt
    int randomColor() {
        RandomColorUtil random = new RandomColorUtil();
        return random.randomColorGenerator();
    }

    public Project createProject(String name) {
        int projectColor = randomColor();
        return new Project(name, projectColor);
    }
}
