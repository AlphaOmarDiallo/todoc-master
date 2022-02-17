package com.cleanup.todoc.repository;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.model.Task;

import java.util.List;

public interface TaskRepository {
    LiveData<List<Task>> getAllTasks();

    void insertTask(Task task);

    void updateTask(Task task);

    void deleteTask(Task task);
}
