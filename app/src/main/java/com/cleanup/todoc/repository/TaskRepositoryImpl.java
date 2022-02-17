package com.cleanup.todoc.repository;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.model.TaskDao;

import java.util.List;
import java.util.concurrent.ExecutorService;

import javax.inject.Inject;

public class TaskRepositoryImpl implements TaskRepository {

    public TaskDao taskDao;
    public ExecutorService executorService;
    private LiveData<List<Task>> allTasks;

    @Inject
    public TaskRepositoryImpl(TaskDao taskDao, ExecutorService executorService, LiveData<List<Task>> allTasks) {
        this.taskDao = taskDao;
        this.executorService = executorService;
        this.allTasks = taskDao.getTasks();
    }

    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }

    public void insertTask(Task task) {
        executorService.execute(() -> taskDao.insertTask(task));
    }

    public void updateTask(Task task) {
        executorService.execute(() -> taskDao.updateTask(task));
    }

    public void deleteTask(Task task) {
        executorService.execute(() -> taskDao.deleteTask(task));
    }
}
