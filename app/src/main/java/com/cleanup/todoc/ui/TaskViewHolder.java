package com.cleanup.todoc.ui;

import android.content.res.ColorStateList;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.cleanup.todoc.R;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.utils.ProjectListUtil;

import java.util.List;

public class TaskViewHolder extends RecyclerView.ViewHolder {

    private static final String TAG = "TAG";
    List<Project> projects;

    ImageView imgProject;
    TextView lblTaskName;
    TextView lblProjectName;
    AppCompatImageView imgDelete;
    static DeleteTaskListener deleteTaskListener;

    public TaskViewHolder(@NonNull View itemView, DeleteTaskListener deleteTaskListener) {
        super(itemView);
        imgProject = itemView.findViewById(R.id.img_project);
        lblTaskName = itemView.findViewById(R.id.lbl_task_name);
        lblProjectName = itemView.findViewById(R.id.lbl_project_name);
        imgDelete = itemView.findViewById(R.id.img_delete);
        this.deleteTaskListener = deleteTaskListener;
    }

    public void bind(Task task) {
        Project project = null;
        try{
            for(Project projectX: projects){
                if (task.getProjectId() == projectX.getId()) {
                    project = projectX;
                    lblProjectName.setText(project.getName());
                    imgProject.setImageTintList(ColorStateList.valueOf((project.getColor())));
                } else {
                    project = null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "bind: " + projects, null);
        }
        lblTaskName.setText(task.getName());
        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Object tag = view.getTag();
                if (tag instanceof Task) {
                    TaskViewHolder.this.deleteTaskListener.onDeleteTask((Task) tag);
                }
            }
        });
    }

    static TaskViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(view, deleteTaskListener);
    }
}
