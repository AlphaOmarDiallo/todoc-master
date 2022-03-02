package com.cleanup.todoc.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProjectDao {
    @Query("SELECT * FROM project_table")
    LiveData<List<Project>> getAllProjects();

    @Query("SELECT * FROM project_table")
    List<Project> getListProjects();

    @Insert
    void insertProject(Project project);

    @Delete
    void deleteProject(Project project);
}
