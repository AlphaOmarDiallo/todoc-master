package com.cleanup.todoc.testDi;

import com.cleanup.todoc.di.ProjectModule;
import com.cleanup.todoc.repository.ProjectRepository;
import com.cleanup.todoc.repository.ProjectRepositoryImpl;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.android.components.ViewModelComponent;
import dagger.hilt.testing.TestInstallIn;


@TestInstallIn(
        components = ViewModelComponent.class,
        replaces = ProjectModule.class
)
@Module
public abstract class TestProjectModule {
    @Binds
    public abstract ProjectRepository bindProjectRepository(ProjectRepositoryImpl impl);
}
