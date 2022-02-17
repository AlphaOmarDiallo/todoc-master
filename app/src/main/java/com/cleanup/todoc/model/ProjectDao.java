package com.cleanup.todoc.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProjectDao {
    @Query("SELECT * FROM project_table")
    LiveData<List<Project>> getAllProjects();

    @Query ("SELECT * FROM project_table WHERE project_id = :id")
    LiveData<Project> getProjectById(long id);
}
