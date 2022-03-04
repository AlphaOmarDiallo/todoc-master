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
import java.util.Objects;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ProjectActivityViewModel extends ViewModel {

    private final ProjectRepositoryImpl projectRepositoryImpl;
    private final TaskRepositoryImpl taskRepositoryImpl;
    LiveData<List<Project>> allProjects;

    @Inject
    public ProjectActivityViewModel(ProjectRepositoryImpl projectRepositoryImpl, TaskRepositoryImpl taskRepositoryImpl) {
        this.projectRepositoryImpl = projectRepositoryImpl;
        this.taskRepositoryImpl = taskRepositoryImpl;
        allProjects = projectRepositoryImpl.getAllProjects();
    }

    public LiveData<List<Project>> getAllProjects() {
        return allProjects;
    }

    public void insertProject(Project project) {
        projectRepositoryImpl.insertProject(project);
    }

    public void deleteProject(Project project) {
        projectRepositoryImpl.deleteProject(project);
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

    public boolean areTasksAssignedToProject(Project project, List<Task> taskList) {
        boolean asNoTaskAttached = true;
        Log.e(TAG, "areTasksAssignedToProject: taskList " + taskList.size(), null);
        for (int i = 0; i < taskList.size(); i++) {
            Task task = Objects.requireNonNull(taskList.get(i));
            if (task.getProject().getId() == project.getId()) {
                asNoTaskAttached = false;
                break;
            }
        }
        return asNoTaskAttached;
    }

    public LiveData<List<Task>> getAllTasks() {
        return taskRepositoryImpl.getAllTasks();
    }
}
