package com.example.paxha.e_sheet.project.create.project;

import com.example.paxha.e_sheet.db.DatabaseHelper;

public interface CreateProjectInteractor {

    public interface onCreateProjectFinishListener {

        void onProjectNameError(String message);
        void onSuccess();
        void onFailure(String message);

    }

    void createProject(String projectName, DatabaseHelper db, onCreateProjectFinishListener listener);

}
