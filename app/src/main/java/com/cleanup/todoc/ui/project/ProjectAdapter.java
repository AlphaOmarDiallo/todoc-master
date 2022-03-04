package com.cleanup.todoc.ui.project;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.cleanup.todoc.model.Project;

public class ProjectAdapter extends ListAdapter<Project, ProjectViewHolder> {

    DeleteProjectListener deleteProjectListener;

    protected ProjectAdapter(@NonNull DiffUtil.ItemCallback<Project> diffCallback, DeleteProjectListener deleteProjectListener) {
        super(diffCallback);
        this.deleteProjectListener = deleteProjectListener;
    }

    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ProjectViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder holder, int position) {
        holder.bind(getItem(position), deleteProjectListener);
    }

    static class ProjectDiff extends DiffUtil.ItemCallback<Project> {

        @Override
        public boolean areItemsTheSame(@NonNull Project oldItem, @NonNull Project newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Project oldItem, @NonNull Project newItem) {
            return oldItem.getId() == newItem.getId() &&
                    oldItem.getColor() == newItem.getColor() &&
                    oldItem.getName().equals(newItem.getName());
        }
    }
}

