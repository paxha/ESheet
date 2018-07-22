package com.example.paxha.e_sheet.project.create.project;


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
public class CreateProjectFragment extends Fragment {

    DatabaseHelper db;

    public CreateProjectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_project, container, false);

        db = new DatabaseHelper(getContext());

        final EditText etProjectName = view.findViewById(R.id.et_project_name);
        Button buttonCreateProject = view.findViewById(R.id.button_create_project);
        buttonCreateProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProjectModel projectModel = new ProjectModel();
                projectModel.setName(etProjectName.getText().toString().trim());
                db.createProject(projectModel);
                getFragmentManager().popBackStackImmediate();
            }
        });
        return view;
    }

}
