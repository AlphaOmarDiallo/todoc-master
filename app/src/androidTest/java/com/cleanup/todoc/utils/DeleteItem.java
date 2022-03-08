package com.cleanup.todoc.utils;

import android.view.View;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;

import com.cleanup.todoc.R;

import org.hamcrest.Matcher;

public class DeleteItem implements ViewAction {
    @Override
    public Matcher<View> getConstraints() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Clicked on delete button";
    }

    @Override
    public void perform(UiController uiController, View view) {
        View button = view.findViewById(R.id.img_delete);
        button.performClick();
    }
}
