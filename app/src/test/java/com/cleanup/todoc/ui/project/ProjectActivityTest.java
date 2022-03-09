package com.cleanup.todoc.ui.project;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.utils.RandomColorUtil;

import org.junit.Test;

public class ProjectActivityTest {
    RandomColorUtil randomColorUtil = mock(RandomColorUtil.class);
    int color1 = randomColorUtil.randomColorGenerator();
    int color2 = randomColorUtil.randomColorGenerator();

    final Project project1 = new Project("project1", color1);
    final Project project2 = new Project("project2", color2);

    @Test
    public void test_projects() {

        assertEquals("project1", project1.getName());
        assertEquals("project2", project2.getName());

        project1.setId(0);
        project2.setId(1);

        assertNotEquals(project1.getId(), project2.getId());
    }

}