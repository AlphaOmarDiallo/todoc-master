package com.cleanup.todoc.ui.project;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.google.common.truth.Truth.assertThat;

import static org.hamcrest.CoreMatchers.allOf;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.cleanup.todoc.R;
import com.cleanup.todoc.model.AppDataBase;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.ProjectDao;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.testing.HiltAndroidRule;
import dagger.hilt.android.testing.HiltAndroidTest;

@HiltAndroidTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProjectActivitiesTest {

    @Inject
    AppDataBase dataBase;

    @Inject
    ProjectDao projectDao;

    List<Project> allProjects;

    int projectsSize;

    @Rule
    public HiltAndroidRule hiltAndroidRule = new HiltAndroidRule(this);

    @Rule
    public ActivityScenarioRule<ProjectActivity> activityScenarioRule = new ActivityScenarioRule<>(ProjectActivity.class);

    @Before
    public void init() {
        hiltAndroidRule.inject();
        getAllProjects();
        projectsSize = getProjectSize();
    }

    @Test
    public void list_is_displayed(){
        onView(allOf(withId(R.id.rvProject), isCompletelyDisplayed()))
                .check(matches(hasChildCount(getProjectSize())));
    }

    @Test
    public void A_cancel_adding_project() {
        onView(withId(R.id.button_add_project)).perform(click());
        onView(withId(R.id.button_annuler)).perform(click());
        assertThat(projectsSize).isEqualTo(getProjectSize());
    }

    @Test
    public void B_project_not_added_if_name_no_set(){
        onView(withId(R.id.button_add_project)).perform(click());
        onView(withId(R.id.button_add_project_aap)).perform(click());
        assertThat(projectsSize).isEqualTo(getProjectSize());
    }

    @Test
    public void C_project_is_added() throws InterruptedException {
        onView(withId(R.id.button_add_project)).perform(click());
        onView(withId(R.id.tv_project_name_aap)).perform(typeText("New Test Project to Add"));
        onView(withId(R.id.button_add_project_aap)).perform(click());
        Thread.sleep(1000);
        assertThat(projectsSize).isLessThan(getProjectSize());
    }

    void getAllProjects(){
        this.allProjects = dataBase.projectDao().getAllProjectsTestUsage();
    }

    int getProjectSize(){
        return dataBase.projectDao().getAllProjectsTestUsage().size();
    }

}