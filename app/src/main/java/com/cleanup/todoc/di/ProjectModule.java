package com.cleanup.todoc.di;

import com.cleanup.todoc.repository.ProjectRepository;
import com.cleanup.todoc.repository.ProjectRepositoryImpl;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;

@InstallIn(ViewModelComponent.class)
@Module
public abstract class ProjectModule {
    @Binds
    public abstract ProjectRepository bindProjectRepository(ProjectRepositoryImpl impl);
}
