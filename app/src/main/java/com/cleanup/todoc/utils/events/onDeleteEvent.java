package com.cleanup.todoc.utils.events;

import com.cleanup.todoc.model.Task;

public class onDeleteEvent {

    public Task task;

    public onDeleteEvent (Task task) {
        this.task = task;
    }
}
