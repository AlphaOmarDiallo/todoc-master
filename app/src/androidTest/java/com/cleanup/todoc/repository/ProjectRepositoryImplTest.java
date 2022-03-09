package com.cleanup.todoc.repository;

import static com.google.common.truth.Truth.assertThat;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.inject.Inject;

import dagger.hilt.android.testing.HiltAndroidRule;
import dagger.hilt.android.testing.HiltAndroidTest;

@HiltAndroidTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProjectRepositoryImplTest {

    @Inject
    ProjectRepositoryImpl projectRepository;

    @Rule
    public HiltAndroidRule hiltAndroidRule = new HiltAndroidRule(this);

    @Before
    public void setUp() throws Exception {
        hiltAndroidRule.inject();
    }

    @Test
    public void A_projectRepository_is_not_null() {
        assertThat(projectRepository).isNotNull();
    }

    @Test
    public void B_getAllProjects_is_not_null() {
        assertThat(projectRepository.getAllProjectsTestUsage()).isNotNull();
    }

}