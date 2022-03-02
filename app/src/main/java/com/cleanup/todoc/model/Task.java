package com.cleanup.todoc.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Comparator;

@Entity(tableName = "task_table", foreignKeys = @ForeignKey(entity = Project.class, parentColumns = "project_id", childColumns = "task_projectId", onDelete = ForeignKey.CASCADE))
public class Task {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "task_id")
    private long id;

    @ColumnInfo(name = "task_projectId", index = true)
    private long projectId;

    @ColumnInfo(name = "task_name")
    private String name;

    @ColumnInfo(name = "task_creationTimestamp")
    private long creationTimestamp;

    @Embedded
    Project project;

    public Task(long projectId, String name, long creationTimestamp, Project project) {
        this.projectId = projectId;
        this.name = name;
        this.creationTimestamp = creationTimestamp;
        this.project = project;
    }

    public long getId() {
        return id;
    }

    private void setProjectId(long projectId) {
        this.projectId = projectId;
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

    public long getProjectId() {
        return projectId;
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

    // ============================================================================
    // COMPARATORS

    /**
     * Comparator to sort task from A to Z
     */
    public static class TaskAZComparator implements Comparator<Task> {
        @Override
        public int compare(Task left, Task right) {
            return left.name.compareTo(right.name);
        }
    }

    /**
     * Comparator to sort task from Z to A
     */
    public static class TaskZAComparator implements Comparator<Task> {
        @Override
        public int compare(Task left, Task right) {
            return right.name.compareTo(left.name);
        }
    }

    /**
     * Comparator to sort task from last created to first created
     */
    public static class TaskRecentComparator implements Comparator<Task> {
        @Override
        public int compare(Task left, Task right) {
            return (int) (right.creationTimestamp - left.creationTimestamp);
        }
    }

    /**
     * Comparator to sort task from first created to last created
     */
    public static class TaskOldComparator implements Comparator<Task> {
        @Override
        public int compare(Task left, Task right) {
            return (int) (left.creationTimestamp - right.creationTimestamp);
        }
    }
}
