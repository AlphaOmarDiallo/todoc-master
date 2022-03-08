package com.cleanup.todoc.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "task_table")
public class Task {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "task_id")
    private long id;

    @ColumnInfo(name = "task_name")
    private String name;

    @ColumnInfo(name = "task_creationTimestamp")
    private long creationTimestamp;

    @Embedded
    Project project;

    public Task(String name, long creationTimestamp, Project project) {
        this.name = name;
        this.creationTimestamp = creationTimestamp;
        this.project = project;
    }

    public long getId() {
        return id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    private void setName(@NonNull String name) {
        this.name = name;
    }

    private void setCreationTimestamp(long creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public long getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Project getProject() {
        return project;
    }
}
