package com.cleanup.todoc.ui.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cleanup.todoc.R;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.viewmodel.ProjectActivityViewModel;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AddProjectActivity extends AppCompatActivity {
    ProjectActivityViewModel viewModel;
    EditText projectName;
    Button addNewProject;
    Button cancel;
    List<Project> allProjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ProjectActivityViewModel.class);
        setContentView(R.layout.activity_add_project);

        projectName = findViewById(R.id.tv_project_name_aap);
        addNewProject = findViewById(R.id.button_add_project_aap);
        cancel = findViewById(R.id.button_annuler);

        viewModel.getAllProjects().observe(this, this::getAllProjects);

        addNewProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (projectName.getText().toString().trim().length() == 0) {
                    Toast.makeText(AddProjectActivity.this, R.string.empty_project_name, Toast.LENGTH_SHORT).show();
                } else {
                   viewModel.insertProject(viewModel.createProject(allProjects, projectName.getText().toString()));
                }
            }
        });

    }

    private List<Project> getAllProjects(List<Project> projects){
        allProjects = projects;
        return allProjects;
    }
}