package com.cleanup.todoc.repository;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.model.Task;

import java.util.List;

public interface TaskRepository {
    LiveData<List<Task>> getAllTasks();

    List<Task> getTasksTestUsage();

    LiveData<List<Task>> taskByAlphabeticalOrder();

    LiveData<List<Task>> taskByAlphabeticalOrder_DESC();

    LiveData<List<Task>> taskByCreationOrder();

    LiveData<List<Task>> taskByCreationOrder_DESC();

    void insertTask(Task task);

    void deleteTask(Task task);
}
