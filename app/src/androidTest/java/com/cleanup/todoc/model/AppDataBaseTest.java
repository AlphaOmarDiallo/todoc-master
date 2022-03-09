package com.cleanup.todoc.model;

import static com.google.common.truth.Truth.assertThat;

import android.graphics.Color;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.testing.HiltAndroidRule;
import dagger.hilt.android.testing.HiltAndroidTest;

@HiltAndroidTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AppDataBaseTest {

    @Inject
    AppDataBase dataBase;

    @Inject
    TaskDao taskDao;

    @Inject
    ProjectDao projectDao;

    List<Task> allTasks;

    List<Project> allProjects;

    int taskSize;

    int projectSize;

    Project project = new Project("Test Project", Color.argb(255, 206, 10, 36));

    Task task = new Task("Test Task", new Date().getTime(), project);

    @Rule
    public HiltAndroidRule hiltAndroidRule = new HiltAndroidRule(this);

    @Before
    public void init() {
        hiltAndroidRule.inject();
        getAllTasks(dataBase.taskDao().getTasksTestUsage());
        getAllProject(dataBase.projectDao().getAllProjectsTestUsage());
    }

    @Test
    public void A_dataBase_is_not_null() {
        assertThat(dataBase).isNotNull();
    }

    @Test
    public void B_taskDao_is_not_null() {
        assertThat(taskDao).isNotNull();
    }

    @Test
    public void C_projectDao_is_not_null() {
        assertThat(projectDao).isNotNull();
    }

    @Test
    public void D_add_new_project() {
        if (allProjects.size() == 0) {
            assertThat(dataBase.projectDao().getAllProjectsTestUsage()).isEmpty();
        } else {
            assertThat(dataBase.taskDao().getTasksTestUsage()).isNotNull();
        }
        projectSize = getProjectSize();
        dataBase.projectDao().insertProject(project);
        assertThat(projectSize).isLessThan(getProjectSize());
    }

    @Test
    public void E_add_new_task() {
        if (allTasks.size() == 0) {
            assertThat(dataBase.taskDao().getTasksTestUsage()).isEmpty();
        } else {
            assertThat(dataBase.taskDao().getTasksTestUsage()).isNotNull();
        }
        projectSize = getProjectSize();
        dataBase.taskDao().insertTask(task);
        getAllTasks(dataBase.taskDao().getTasksTestUsage());
        assertThat(taskSize).isLessThan(allTasks.size());
    }

    @Test
    public void F_delete_task() {
        taskSize = getTaskSize();
        dataBase.taskDao().deleteTask(dataBase.taskDao().getTasksTestUsage().get(taskSize - 1));
        assertThat(taskSize).isGreaterThan(getTaskSize());
    }

    @Test
    public void G_delete_project() {
        projectSize = getProjectSize();
        dataBase.projectDao().deleteProject(dataBase.projectDao().getAllProjectsTestUsage().get(projectSize - 1));
        assertThat(projectSize).isGreaterThan(getProjectSize());
    }

    // ================================ Methods related to tests =========================================

    void getAllTasks(List<Task> allTasks) {
        this.allTasks = allTasks;
    }

    void getAllProject(List<Project> allProjects) {
        this.allProjects = allProjects;
    }

    int getTaskSize() {
        return dataBase.taskDao().getTasksTestUsage().size();
    }

    int getProjectSize() {
        return dataBase.projectDao().getAllProjectsTestUsage().size();
    }
}