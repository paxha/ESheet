package com.example.paxha.e_sheet.project;

import com.example.paxha.e_sheet.db.DatabaseHelper;

public interface ProjectInteractor {

    interface onCreateProjectFinishListener {

        void onProjectNameError(String message);

        void onSuccess();

        void onFailure(String message);

    }

    void createProject(String projectName, DatabaseHelper db, onCreateProjectFinishListener listener);

    void updateProject(int id, String projectName, DatabaseHelper db, onCreateProjectFinishListener listener);

}
