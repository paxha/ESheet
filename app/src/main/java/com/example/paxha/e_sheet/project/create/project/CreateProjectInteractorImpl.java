package com.example.paxha.e_sheet.project.create.project;

import android.text.TextUtils;

import com.example.paxha.e_sheet.db.DatabaseHelper;
import com.example.paxha.e_sheet.project.ProjectModel;

import java.util.regex.Pattern;

public class CreateProjectInteractorImpl implements CreateProjectInteractor {
    @Override
    public void createProject(String projectName, DatabaseHelper db, onCreateProjectFinishListener listener) {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9!@#$%^&*()?]*");
        if (TextUtils.isEmpty(projectName))
            listener.onProjectNameError("Project name is empty");
        else if (!pattern.matcher(projectName).matches())
            listener.onProjectNameError("Invalid name");
        else {
            ProjectModel projectModel = new ProjectModel();
            projectModel.setName(projectName);
            int r = db.createProject(projectModel);
            if (r > 1)
                listener.onSuccess();
            else
                listener.onFailure("Something went wrong");
        }
    }
}
