package com.cleanup.todoc.model;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "project_table")
public class Project {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "project_id")
    private long id;

    @NonNull
    @ColumnInfo(name = "project_name")
    private final String name;

    @ColorInt
    @ColumnInfo(name = "project_color")
    private final int color;

    public Project(@NonNull String name, @ColorInt int color) {
        this.name = name;
        this.color = color;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @Override
    @NonNull
    public String toString() {
        return getName();
    }

    @ColorInt
    public int getColor() {
        return color;
    }
}
