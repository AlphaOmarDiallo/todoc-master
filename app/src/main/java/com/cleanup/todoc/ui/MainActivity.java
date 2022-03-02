package com.cleanup.todoc.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cleanup.todoc.R;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.ui.project.ProjectActivity;
import com.cleanup.todoc.viewmodel.MainActivityViewModel;

import java.util.Date;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity implements DeleteTaskListener, TaskAdapter.ToDeleteTaskListener {

    private static final String SORT_METHOD_KEY = "SORT_METHOD_KEY";
    public MainActivityViewModel viewModel;
    private List<Project> allProjects;
    private final TaskAdapter adapter = new TaskAdapter(new TaskAdapter.TaskDiff());
    @NonNull
    private SortMethod sortMethod = SortMethod.NONE;
    @Nullable
    public AlertDialog dialog = null;
    @Nullable
    private EditText dialogEditText = null;
    @Nullable
    private Spinner dialogSpinner = null;
    private RecyclerView listTasks;
    private TextView lblNoTasks;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        setContentView(R.layout.activity_main);

        listTasks = findViewById(R.id.list_tasks);
        lblNoTasks = findViewById(R.id.lbl_no_task);

        listTasks.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        listTasks.setAdapter(adapter);

        findViewById(R.id.fab_add_task).setOnClickListener(view -> showAddTaskDialog());

        setViewModel();
    }

    private void setViewModel() {
        viewModel.getAllProjects().observe(this, this::updateProjects);
        viewModel.getAllTasks().observe(this, adapter::submitList);
        viewModel.getAllTasks().observe(this, this::updateTasks);
    }

    private void updateProjects(List<Project> projects) {
        this.allProjects = projects;
    }

    public void updateTasks(List<Task> tasks) {
        if (tasks.size() == 0) {
            lblNoTasks.setVisibility(View.VISIBLE);
            listTasks.setVisibility(View.GONE);
        } else {
            lblNoTasks.setVisibility(View.GONE);
            listTasks.setVisibility(View.VISIBLE);
        }
    }

    private void addTask(@NonNull Task task) {
        viewModel.insertTask(task);
    }

    @Override
    public void onDeleteTask(Task task) {
        viewModel.deleteTask(task);
    }

    @Override
    public void onToDeleteTask(int position) {
        List<Task> tasks = (List<Task>) viewModel.getAllTasks();
        Task task = tasks.get(position);
        viewModel.deleteTask(task);
    }

    //=================== Menu ===========================================================
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.filter_alphabetical) {
            viewModel.setTaskByAlphabeticalOrderASC();
            sortMethod = SortMethod.ALPHABETICAL;
        } else if (id == R.id.filter_alphabetical_inverted) {
            sortMethod = SortMethod.ALPHABETICAL_INVERTED;
            viewModel.setTaskByAlphabeticalOrderDesc();
        } else if (id == R.id.filter_oldest_first) {
            sortMethod = SortMethod.OLD_FIRST;
            viewModel.setTaskByCreationOrder();
        } else if (id == R.id.filter_recent_first) {
            sortMethod = SortMethod.RECENT_FIRST;
            viewModel.setTaskByCreationOrderDesc();
        } else if (id == R.id.add_project) {
            Intent intent = new Intent(this, ProjectActivity.class);
            startActivity(intent);
        }
        viewModel.getAllTasks().observe(this, adapter::submitList);
        return super.onOptionsItemSelected(item);
    }

    // ========================== Dialog task creation =======================================================
    private void showAddTaskDialog() {
        final AlertDialog dialog = getAddTaskDialog();

        dialog.show();

        dialogEditText = dialog.findViewById(R.id.txt_task_name);
        dialogSpinner = dialog.findViewById(R.id.project_spinner);

        populateDialogSpinner();
    }

    private void populateDialogSpinner() {
        final ArrayAdapter<Project> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, allProjects);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if (dialogSpinner != null) {
            dialogSpinner.setAdapter(adapter);
        }
    }

    @NonNull
    private AlertDialog getAddTaskDialog() {
        final AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this, R.style.Dialog);

        alertBuilder.setTitle(R.string.add_task);
        alertBuilder.setView(R.layout.dialog_add_task);
        alertBuilder.setPositiveButton(R.string.add, null);
        alertBuilder.setOnDismissListener(dialogInterface -> {
            dialogEditText = null;
            dialogSpinner = null;
            dialog = null;
        });

        dialog = alertBuilder.create();

        // This instead of listener to positive button in order to avoid automatic dismiss
        dialog.setOnShowListener(dialogInterface -> {

            Button button = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
            button.setOnClickListener(view -> onPositiveButtonClick(dialog));
        });
        return dialog;
    }

    private void onPositiveButtonClick(DialogInterface dialogInterface) {
        // If dialog is open
        if (dialogEditText != null && dialogSpinner != null) {
            // Get the name of the task
            String taskName = dialogEditText.getText().toString();
            // Get the selected project to be associated to the task
            Project taskProject = null;
            if (dialogSpinner.getSelectedItem() instanceof Project) {
                taskProject = (Project) dialogSpinner.getSelectedItem();
            }
            // If a name has not been set
            if (taskName.trim().isEmpty()) {
                dialogEditText.setError(getString(R.string.empty_task_name));
            }
            // If both project and name of the task have been set
            else if (taskProject != null) {
                Task task = new Task(
                        taskProject.getId(),
                        taskName,
                        new Date().getTime(),
                        taskProject
                );

                addTask(task);

                dialogInterface.dismiss();
            }
            // If name has been set, but project has not been set (this should never occur)
            else {
                dialogInterface.dismiss();
            }
        }
        // If dialog is already closed
        else {
            dialogInterface.dismiss();
        }
    }

    // ============================== LifeCycle related =================================================
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(SORT_METHOD_KEY, sortMethod.name());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        String previousSortMethodString = savedInstanceState.getString(SORT_METHOD_KEY);
        SortMethod previousSortMethod = SortMethod.NONE;
        for (SortMethod equalSortMethod : SortMethod.values()) {
            if (equalSortMethod.name().equals(previousSortMethodString)) {
                previousSortMethod = equalSortMethod;
            }
        }
        if (sortMethod != previousSortMethod) {
            sortMethod = previousSortMethod;
            switch (sortMethod) {
                case ALPHABETICAL:
                    viewModel.setTaskByAlphabeticalOrderASC();
                    break;
                case ALPHABETICAL_INVERTED:
                    viewModel.setTaskByAlphabeticalOrderDesc();
                    break;
                case RECENT_FIRST:
                    viewModel.setTaskByCreationOrderDesc();
                    break;
                case OLD_FIRST:
                    viewModel.setTaskByCreationOrder();
                    break;
            }
            viewModel.getAllTasks().observe(this, adapter::submitList);
        }

    }

    private enum SortMethod {
        ALPHABETICAL,
        ALPHABETICAL_INVERTED,
        RECENT_FIRST,
        OLD_FIRST,
        NONE
    }
}

