package com.cleanup.todoc.ui;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.cleanup.todoc.model.Task;

import java.util.List;

public class ListTaskAdapter extends ListAdapter<Task, ListTaskViewHolder> {

    List<Task> tasks;
    protected ListTaskAdapter(@NonNull DiffUtil.ItemCallback<Task> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public ListTaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ListTaskViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ListTaskViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    static class TaskDiff extends DiffUtil.ItemCallback<Task> {

        @Override
        public boolean areItemsTheSame(@NonNull Task oldItem, @NonNull Task newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Task oldItem, @NonNull Task newItem) {
            if (oldItem.getId() == newItem.getId() &&
                    oldItem.getName().equals(newItem.getName()) &&
                    oldItem.getProjectId() == newItem.getProjectId() &&
                    oldItem.getCreationTimestamp() == newItem.getCreationTimestamp()
            ) {
                return true;
            } else {
                return false;
            }
        }
    }

    void _updateTasks(@NonNull final List<Task> tasks) {
        this.tasks = tasks;
        notifyDataSetChanged();
    }
}
