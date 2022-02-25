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
    public LiveData<List<Task>> allTasks;

    @Inject
    public TaskRepositoryImpl(TaskDao taskDao, ExecutorService executorService, LiveData<List<Task>> allTasks) {
        this.taskDao = taskDao;
        this.executorService = executorService;
        this.allTasks = allTasks;
    }

    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }

    public void insertTask(Task task) {
        executorService.execute(() -> taskDao.insertTask(task));
    }

    public void deleteTask(Task task) {
        executorService.execute(() -> taskDao.deleteTask(task));
    }

    public LiveData<List<Task>> taskByAlphabeticalOrder() {
        return taskDao.taskByAlphabeticalOrder();
    }
}
