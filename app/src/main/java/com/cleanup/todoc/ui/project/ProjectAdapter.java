package com.cleanup.todoc.ui.project;

import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.cleanup.todoc.R;
import com.cleanup.todoc.model.Project;

public class ProjectAdapter extends ListAdapter<Project, ProjectViewHolder> {

    protected ProjectAdapter(@NonNull DiffUtil.ItemCallback<Project> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ProjectViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    class ProjectDiff extends DiffUtil.ItemCallback<Project>{

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

