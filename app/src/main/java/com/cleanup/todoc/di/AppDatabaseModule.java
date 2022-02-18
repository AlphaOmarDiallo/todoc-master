package com.cleanup.todoc.di;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.cleanup.todoc.model.AppDataBase;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.ProjectDao;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.model.TaskDao;

import java.util.List;
import java.util.concurrent.ExecutorService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@InstallIn(SingletonComponent.class)
@Module
public class AppDatabaseModule {
    @Provides
    @Singleton
    AppDataBase provideDatabase(@ApplicationContext Context appContext) {
        return Room.databaseBuilder(
                appContext,
                AppDataBase.class,
                "Tasks_db"
        ).build();
    }

    @Provides
    public TaskDao provideTaskDao(AppDataBase dataBase) {
        return dataBase.taskDao();
    }

    @Provides
    public ProjectDao provideProjectDao(AppDataBase dataBase) {
        return dataBase.projectDao();
    }

    @Provides
    @Singleton
    public ExecutorService provideExecutorService(AppDataBase dataBase) {
        return dataBase.executorService;
    }

    @Provides
    public LiveData<List<Project>> provideAllProject(AppDataBase dataBase) {
        return dataBase.projectDao().getAllProjects();
    }

    @Provides
    public LiveData<List<Task>> provideAllTasks(AppDataBase dataBase) {
        return dataBase.taskDao().getTasks();
    }
}
