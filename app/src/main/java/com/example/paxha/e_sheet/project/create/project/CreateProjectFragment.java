package com.example.paxha.e_sheet.project.create.project;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.paxha.e_sheet.R;
import com.example.paxha.e_sheet.db.DatabaseHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateProjectFragment extends Fragment implements CreateProjectView {

    DatabaseHelper db;
    EditText etProjectName;
    ProgressBar progressBar;
    CreateProjectPresenter presenter;
    View view;

    public CreateProjectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_create_project, container, false);

        db = new DatabaseHelper(getContext());

        etProjectName = view.findViewById(R.id.et_project_name);
        progressBar = view.findViewById(R.id.progress_bar);
        Button buttonCreateProject = view.findViewById(R.id.button_create_project);
        buttonCreateProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter = new CreateProjectPresenterImpl(CreateProjectFragment.this);
                presenter.onValidate(etProjectName.getText().toString().trim(), db);
            }
        });
        return view;
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setProjectNameError(String message) {
        etProjectName.setError(message);
    }

    @Override
    public void navigateToNext() {
        getFragmentManager().popBackStackImmediate();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
