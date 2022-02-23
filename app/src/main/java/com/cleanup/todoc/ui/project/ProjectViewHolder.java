package com.cleanup.todoc.ui.project;

import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cleanup.todoc.R;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

public class ProjectViewHolder extends RecyclerView.ViewHolder{

    DeleteProjectListener deleteProjectListener;
    ImageView colorImage;
    TextView id;
    TextView name;
    ImageButton buttonDelete;

    public ProjectViewHolder(@NonNull View itemView) {
        super(itemView);

        colorImage = itemView.findViewById(R.id.iv_item_project);
        id = itemView.findViewById(R.id.tv_id_Item_project);
        name = itemView.findViewById(R.id.tv_name_item_project);
        buttonDelete = itemView.findViewById(R.id.ib_delete_item_project);

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Object tag = view.getTag();
                if (tag instanceof Project) {
                    ProjectViewHolder.this.deleteProjectListener.onDeleteProject((Project) tag);
                }
            }
        });
    }

    public void bind(Project project) {
        colorImage.setImageTintList(ColorStateList.valueOf(project.getColor()));
        id.setText(String.valueOf(project.getId()));
        name.setText(project.getName());
    }

    static ProjectViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_project, parent, false);
        return new ProjectViewHolder(view);
    }
}
