package com.cleanup.todoc.ui.project;
import static android.content.ContentValues.TAG;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;

import android.util.Log;

import androidx.annotation.ColorInt;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.utils.RandomColorUtil;

import org.junit.Test;

public class ProjectActivityTest {
    RandomColorUtil randomColorUtil = mock(RandomColorUtil.class);
    int color1 = randomColorUtil.randomColorGenerator();
    int color2 = randomColorUtil.randomColorGenerator();

    final Project project1 = new Project(1, "project1", color1);
    final Project project2 = new Project(2, "project2", color2);

    @Test
    public void test_projects() {

        assertEquals("project1", project1.getName());
        assertEquals("project2", project2.getName());

        assertNotEquals(project1.getId(), project2.getId());
    }

}