package com.example.paxha.e_sheet.project.edit.project;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.paxha.e_sheet.R;
import com.example.paxha.e_sheet.db.DatabaseHelper;
import com.example.paxha.e_sheet.project.ProjectModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditProjectFragment extends Fragment {

    DatabaseHelper db;

    public EditProjectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_project, container, false);

        db = new DatabaseHelper(getContext());

        ProjectModel projectModel = db.getProject(getArguments().getInt("KEY_PROJECT_ID"));

        final EditText etProjectName = view.findViewById(R.id.et_project_name);
        etProjectName.setText(projectModel.getName());

        Button buttonUpdateProject = view.findViewById(R.id.button_update_project);
        buttonUpdateProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProjectModel projectModel = new ProjectModel();
                projectModel.setId(getArguments().getInt("KEY_PROJECT_ID"));
                projectModel.setName(etProjectName.getText().toString().trim());
                db.updateProject(projectModel);
                getFragmentManager().popBackStackImmediate();
            }
        });

        return  view;
    }

}
