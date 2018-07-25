package com.example.paxha.e_sheet.project;

import com.example.paxha.e_sheet.db.DatabaseHelper;

public interface ProjectInteractor {

    interface onFinishListener {

        void onProjectNameError(String message);

        void onSuccess();

        void onFailure(String message);

    }

    void createProject(String projectName, DatabaseHelper db, onFinishListener listener);

    void updateProject(int id, String projectName, DatabaseHelper db, onFinishListener listener);

}
