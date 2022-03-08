package com.cleanup.todoc.ui.main;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.cleanup.todoc.utils.TestUtils.withRecyclerView;
import static com.google.common.truth.Truth.assertThat;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.core.IsNot.not;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.cleanup.todoc.R;
import com.cleanup.todoc.model.AppDataBase;
import com.cleanup.todoc.model.ProjectDao;
import com.cleanup.todoc.model.TaskDao;
import com.cleanup.todoc.utils.DeleteItem;

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
public class MainActivityTest {

    @Rule
    public HiltAndroidRule hiltAndroidRule = new HiltAndroidRule(this);

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    @Inject
    AppDataBase dataBase;
    @Inject
    TaskDao taskDao;
    @Inject
    ProjectDao projectDao;

    @Before
    public void init() {
        hiltAndroidRule.inject();
    }

    @Test
    public void A_dataBase_is_not_null() {
        assertThat(dataBase).isNotNull();
    }

    @Test
    public void B_icon_is_shown_as_list_is_empty() {
        onView(withText("Tu n’as aucune tâche à traiter")).check(matches(isDisplayed()));
    }

    @Test
    public void C_add_three_task() {
        onView(withId(R.id.fab_add_task)).perform(click());
        onView(withId(R.id.txt_task_name)).perform(typeText("AAA"));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.fab_add_task)).perform(click());
        onView(withId(R.id.txt_task_name)).perform(typeText("BBB"));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.fab_add_task)).perform(click());
        onView(withId(R.id.txt_task_name)).perform(typeText("CCC"));
        onView(withId(android.R.id.button1)).perform(click());
    }

    @Test
    public void D_task_are_added_and_lbl_is_gone() throws InterruptedException {
        Thread.sleep(1000);
        onView(allOf(withId(R.id.list_tasks), isCompletelyDisplayed()))
                .check(matches(hasChildCount(3)));
        onView(withText("Tu n’as aucune tâche à traiter")).check(matches(not(isDisplayed())));
    }

    @Test
    public void E_list_sorting_alphabetical_order() {            // Sort alphabetical
        onView(withId(R.id.action_filter)).perform(click());
        onView(withText(R.string.sort_alphabetical)).perform(click());
        onView(withRecyclerView(R.id.list_tasks).atPositionOnView(0, R.id.lbl_task_name))
                .check(matches(withText("AAA")));
        onView(withRecyclerView(R.id.list_tasks).atPositionOnView(1, R.id.lbl_task_name))
                .check(matches(withText("BBB")));
        onView(withRecyclerView(R.id.list_tasks).atPositionOnView(2, R.id.lbl_task_name))
                .check(matches(withText("CCC")));
    }

    @Test
    public void F_list_sorting_alphabetical_inverted_order() {
        onView(withId(R.id.action_filter)).perform(click());
        onView(withText(R.string.sort_alphabetical_invert)).perform(click());
        onView(withRecyclerView(R.id.list_tasks).atPositionOnView(0, R.id.lbl_task_name))
                .check(matches(withText("CCC")));
        onView(withRecyclerView(R.id.list_tasks).atPositionOnView(1, R.id.lbl_task_name))
                .check(matches(withText("BBB")));
        onView(withRecyclerView(R.id.list_tasks).atPositionOnView(2, R.id.lbl_task_name))
                .check(matches(withText("AAA")));
    }

    @Test
    public void G_list_sort_old_first() {
        onView(withId(R.id.action_filter)).perform(click());
        onView(withText(R.string.sort_oldest_first)).perform(click());
        onView(withRecyclerView(R.id.list_tasks).atPositionOnView(0, R.id.lbl_task_name))
                .check(matches(withText("AAA")));
        onView(withRecyclerView(R.id.list_tasks).atPositionOnView(1, R.id.lbl_task_name))
                .check(matches(withText("BBB")));
        onView(withRecyclerView(R.id.list_tasks).atPositionOnView(2, R.id.lbl_task_name))
                .check(matches(withText("CCC")));
    }

    @Test
    public void H_list_sort_recent_first() {
        onView(withId(R.id.action_filter)).perform(click());
        onView(withText(R.string.sort_recent_first)).perform(click());
        onView(withRecyclerView(R.id.list_tasks).atPositionOnView(0, R.id.lbl_task_name))
                .check(matches(withText("CCC")));
        onView(withRecyclerView(R.id.list_tasks).atPositionOnView(1, R.id.lbl_task_name))
                .check(matches(withText("BBB")));
        onView(withRecyclerView(R.id.list_tasks).atPositionOnView(2, R.id.lbl_task_name))
                .check(matches(withText("AAA")));
    }

    @Test
    public void I_delete_items_label_shows() throws InterruptedException {
        Thread.sleep(3000);
        onView(withId(R.id.list_tasks)).perform(RecyclerViewActions.actionOnItemAtPosition(2, new DeleteItem()));
        onView(withId(R.id.list_tasks)).perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteItem()));
        onView(withId(R.id.list_tasks)).perform(RecyclerViewActions.actionOnItemAtPosition(0, new DeleteItem()));
        onView(withText("Tu n’as aucune tâche à traiter")).check(matches(not(isDisplayed())));
    }

}