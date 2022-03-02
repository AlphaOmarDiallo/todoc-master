package com.cleanup.todoc.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TaskDao {

    @Insert
    void insertTask(Task task);

    @Delete
    void deleteTask(Task task);

    @Query("SELECT * FROM task_table")
    LiveData<List<Task>> getTasks();

    @Query("SELECT * FROM task_table ORDER BY task_name")
    LiveData<List<Task>> taskByAlphabeticalOrder();

    @Query("SELECT * FROM task_table ORDER BY task_name DESC")
    LiveData<List<Task>> taskByAlphabeticalOrder_DESC();

    @Query("SELECT * FROM task_table ORDER BY task_creationTimestamp")
    LiveData<List<Task>> taskByCreationOrder();

    @Query("SELECT * FROM task_table ORDER BY task_creationTimestamp DESC")
    LiveData<List<Task>> taskByCreationOrder_DESC();
}
