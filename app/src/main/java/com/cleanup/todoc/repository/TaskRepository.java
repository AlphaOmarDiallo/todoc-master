package com.cleanup.todoc.repository;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.model.Task;

import java.util.List;

public interface TaskRepository {
    LiveData<List<Task>> getAllTasks();

    LiveData<List<Task>> taskByAlphabeticalOrder();

    void insertTask(Task task);

    void deleteTask(Task task);
}
