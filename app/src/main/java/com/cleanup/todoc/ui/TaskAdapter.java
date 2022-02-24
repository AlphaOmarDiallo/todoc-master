package com.cleanup.todoc.ui;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.cleanup.todoc.model.Task;

public class TaskAdapter extends ListAdapter<Task, TaskViewHolder> {
    protected TaskAdapter(@NonNull DiffUtil.ItemCallback<Task> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return TaskViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    static class TaskDiff extends DiffUtil.ItemCallback<Task>{

        @Override
        public boolean areItemsTheSame(@NonNull Task oldItem, @NonNull Task newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Task oldItem, @NonNull Task newItem) {
            return oldItem.getId() == newItem.getId() &&
                    oldItem.getName().equals(newItem.getName()) &&
                    oldItem.getCreationTimestamp() == newItem.getCreationTimestamp() &&
                    oldItem.getProjectId() == newItem.getProjectId();
        }
    }
}
