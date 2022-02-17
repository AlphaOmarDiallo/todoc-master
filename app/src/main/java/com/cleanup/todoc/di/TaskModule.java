package com.cleanup.todoc.di;

import com.cleanup.todoc.repository.TaskRepository;
import com.cleanup.todoc.repository.TaskRepositoryImpl;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;

@InstallIn(ViewModelComponent.class)
@Module
public abstract class TaskModule {
    @Binds
    public abstract TaskRepository bindTaskRepository(TaskRepositoryImpl impl);
}
