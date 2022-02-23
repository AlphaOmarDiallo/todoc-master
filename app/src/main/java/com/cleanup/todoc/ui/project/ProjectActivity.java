package com.cleanup.todoc.ui.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cleanup.todoc.R;
import com.cleanup.todoc.viewmodel.ProjectActivityViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ProjectActivity extends AppCompatActivity {

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

        adapter = new ProjectAdapter(new ProjectAdapter.ProjectDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel.getAllProjects().observe(this, adapter::submitList);

        buttonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProjectActivity.this, AddProjectActivity.class);
                startActivity(intent);
            }
        });
    }
}