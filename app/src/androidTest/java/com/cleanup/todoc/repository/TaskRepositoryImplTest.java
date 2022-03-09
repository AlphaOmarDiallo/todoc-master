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
public class TaskRepositoryImplTest {

    @Inject
    TaskRepositoryImpl taskRepository;

    @Rule
    public HiltAndroidRule hiltAndroidRule = new HiltAndroidRule(this);

    @Before
    public void setUp() throws Exception {
        hiltAndroidRule.inject();
    }

    @Test
    public void A_getAllTasks_is_not_null() {
        assertThat(taskRepository.getTasksTestUsage()).isNotNull();
    }

    @Test
    public void taskByAlphabeticalOrder() {
        assertThat(taskRepository.taskByAlphabeticalOrder()).isNotNull();
    }

    @Test
    public void taskByAlphabeticalOrder_DESC() {
        assertThat(taskRepository.taskByAlphabeticalOrder_DESC()).isNotNull();
    }

    @Test
    public void taskByCreationOrder() {
        assertThat(taskRepository.taskByCreationOrder()).isNotNull();
    }

    @Test
    public void taskByCreationOrder_DESC() {
        assertThat(taskRepository.taskByCreationOrder_DESC()).isNotNull();
    }

}