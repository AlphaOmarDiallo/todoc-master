package com.cleanup.todoc.ui.project;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.cleanup.todoc.R;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.viewmodel.ProjectActivityViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AddProjectActivity extends AppCompatActivity {
    ProjectActivityViewModel viewModel;
    EditText projectName;
    Button addNewProject;
    Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ProjectActivityViewModel.class);
        setContentView(R.layout.activity_add_project);

        projectName = findViewById(R.id.tv_project_name_aap);
        addNewProject = findViewById(R.id.button_add_project_aap);
        cancel = findViewById(R.id.button_annuler);

        addNewProject.setOnClickListener(view -> {
            if (projectName.getText().toString().trim().length() == 0) {
                Toast.makeText(AddProjectActivity.this, R.string.empty_project_name, Toast.LENGTH_SHORT).show();
            } else {
                Project project = viewModel.createProject(projectName.getText().toString());
                viewModel.insertProject(project);
                Toast.makeText(AddProjectActivity.this, R.string.project_created, Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        cancel.setOnClickListener(view -> finish());

    }
}