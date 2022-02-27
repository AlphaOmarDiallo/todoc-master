package com.cleanup.todoc.utils;

import com.cleanup.todoc.model.Project;

import java.util.List;

public class ProjectListUtil {

    public List<Project> projects;

    public void updateProjects(List<Project> projects) {
        this.projects = projects;
    }

    public List<Project> getProjects() {
        return projects;
    }
}
