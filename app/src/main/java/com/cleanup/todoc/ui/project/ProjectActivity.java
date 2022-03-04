package com.cleanup.todoc.ui.project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cleanup.todoc.R;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.viewmodel.ProjectActivityViewModel;

import java.util.List;
import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ProjectActivity extends AppCompatActivity implements DeleteProjectListener {

    List<Task> allTask;

    ProjectActivityViewModel viewModel;
    ProjectAdapter adapter;

    RecyclerView recyclerView;
    Button buttonReturn;
    Button buttonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ProjectActivityViewModel.class);
        setContentView(R.layout.activity_project);

        recyclerView = findViewById(R.id.rvProject);
        buttonReturn = findViewById(R.id.button_return_home);
        buttonAdd = findViewById(R.id.button_add_project);

        adapter = new ProjectAdapter(new ProjectAdapter.ProjectDiff(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        buttonReturn.setOnClickListener(view -> finish());

        buttonAdd.setOnClickListener(view -> {
            Intent intent = new Intent(ProjectActivity.this, AddProjectActivity.class);
            startActivity(intent);
        });

        viewModel.getAllProjects().observe(this, adapter::submitList);
        viewModel.getAllTasks().observe(this, this::updateTask);
    }

    @Override
    public void deleteProject(int position) {
        Project project = Objects.requireNonNull(viewModel.getAllProjects().getValue()).get(position);
        if (viewModel.areTasksAssignedToProject(project, allTask)) {
            viewModel.deleteProject(project);
        } else {
            Toast.makeText(this, R.string.cannot_delete_task_assigned, Toast.LENGTH_SHORT).show();
        }
    }

    public void updateTask(List<Task> listTask) {
        allTask = listTask;
    }
}