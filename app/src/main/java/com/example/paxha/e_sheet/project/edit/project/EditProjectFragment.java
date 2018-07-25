package com.example.paxha.e_sheet.project.edit.project;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.paxha.e_sheet.R;
import com.example.paxha.e_sheet.db.DatabaseHelper;
import com.example.paxha.e_sheet.project.ProjectModel;
import com.example.paxha.e_sheet.project.ProjectPresenter;
import com.example.paxha.e_sheet.project.ProjectPresenterImpl;
import com.example.paxha.e_sheet.project.ProjectView;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditProjectFragment extends Fragment implements ProjectView {

    DatabaseHelper db;
    EditText etProjectName;
    ProgressBar progressBar;
    ProjectPresenter presenter;

    public EditProjectFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_project, container, false);

        db = new DatabaseHelper(getContext());

        final ProjectModel projectModel = db.getProject(getArguments().getInt("KEY_PROJECT_ID"));

        etProjectName = view.findViewById(R.id.et_project_name);
        progressBar = view.findViewById(R.id.progress_bar);

        etProjectName.setText(projectModel.getName());

        Button buttonUpdateProject = view.findViewById(R.id.button_update_project);
        buttonUpdateProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter = new ProjectPresenterImpl(EditProjectFragment.this);
                presenter.onUpdateProject(projectModel.getId(), etProjectName.getText().toString().trim(), db);
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
        hideKeyboard(getContext());
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

    void hideKeyboard(Context context) {
        InputMethodManager manager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        View view = ((Activity) context).getCurrentFocus();
        if (view == null)
            return;
        assert manager != null;
        manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
