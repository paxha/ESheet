package com.example.paxha.e_sheet.project;

import android.text.TextUtils;

import com.example.paxha.e_sheet.db.DatabaseHelper;

import java.util.regex.Pattern;

public class ProjectInteractorImpl implements ProjectInteractor {
    @Override
    public void createProject(String projectName, DatabaseHelper db, onCreateProjectFinishListener listener) {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9!@#$%^&*()? ]*");
        if (TextUtils.isEmpty(projectName))
            listener.onProjectNameError("Project name is empty");
        else if (!pattern.matcher(projectName).matches())
            listener.onProjectNameError("Invalid name");
        else {
            ProjectModel projectModel = new ProjectModel();
            projectModel.setName(projectName);
            int r = db.createProject(projectModel);
            if (r > 0)
                listener.onSuccess();
            else
                listener.onFailure("Something went wrong");
        }
    }

    @Override
    public void updateProject(int id, String projectName, DatabaseHelper db, onCreateProjectFinishListener listener) {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9!@#$%^&*()? ]*");
        if (TextUtils.isEmpty(projectName))
            listener.onProjectNameError("Project name is empty");
        else if (!pattern.matcher(projectName).matches())
            listener.onProjectNameError("Invalid name");
        else {
            ProjectModel projectModel = new ProjectModel();
            projectModel.setId(id);
            projectModel.setName(projectName);
            int r = db.updateProject(projectModel);
            if (r > 0)
                listener.onSuccess();
            else
                listener.onFailure("Something went wrong");
        }
    }
}
