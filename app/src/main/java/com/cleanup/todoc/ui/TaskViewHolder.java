package com.cleanup.todoc.ui;

import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.cleanup.todoc.R;
import com.cleanup.todoc.model.Task;

public class TaskViewHolder extends RecyclerView.ViewHolder {

    ImageView imgProject;
    TextView lblTaskName;
    TextView lblProjectName;
    AppCompatImageView imgDelete;
    static DeleteTaskListener deleteTaskListener;

    public TaskViewHolder(@NonNull View itemView, DeleteTaskListener deleteTaskListener) {
        super(itemView);
        TaskViewHolder.deleteTaskListener = deleteTaskListener;
        imgProject = itemView.findViewById(R.id.img_project);
        lblTaskName = itemView.findViewById(R.id.lbl_task_name);
        lblProjectName = itemView.findViewById(R.id.lbl_project_name);
        imgDelete = itemView.findViewById(R.id.img_delete);
    }

    public void bind(Task task) {
        lblProjectName.setText(task.getProject().getName());
        imgProject.setImageTintList(ColorStateList.valueOf((task.getProject().getColor())));
        lblTaskName.setText(task.getName());
        imgDelete.setTag(task);
        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Object tag = view.getTag();
                if (tag instanceof Task) {
                    deleteTaskListener.onDeleteTask((Task) tag);
                }
            }
        });
    }

    static TaskViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(view, deleteTaskListener);
    }
}
