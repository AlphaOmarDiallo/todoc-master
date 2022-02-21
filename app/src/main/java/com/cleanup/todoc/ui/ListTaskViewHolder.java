package com.cleanup.todoc.ui;

import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.cleanup.todoc.R;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

public class ListTaskViewHolder extends RecyclerView.ViewHolder {
    private final AppCompatImageView imgProject;
    private final TextView lblTaskName;
    private final TextView lblProjectName;
    private final AppCompatImageView imgDelete;

    public ListTaskViewHolder(@NonNull View itemView) {
        super(itemView);

        imgProject = itemView.findViewById(R.id.img_project);
        lblTaskName = itemView.findViewById(R.id.lbl_task_name);
        lblProjectName = itemView.findViewById(R.id.lbl_project_name);
        imgDelete = itemView.findViewById(R.id.img_delete);

        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Object tag = view.getTag();
                if (tag instanceof Task) {
                    //TaskListViewHolder.this.deleteTaskListener.onDeleteTask((Task) tag);
                }
            }
        });
    }

    void bind(Task task) {
        lblTaskName.setText(task.getName());
        imgDelete.setTag(task);

        final Project taskProject = task.getProject();
        if (taskProject != null) {
            imgProject.setSupportImageTintList(ColorStateList.valueOf(taskProject.getColor()));
            lblProjectName.setText(taskProject.getName());
        } else {
            imgProject.setVisibility(View.INVISIBLE);
            lblProjectName.setText("");
        }
    }

    static ListTaskViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new ListTaskViewHolder(view);
    }
}
