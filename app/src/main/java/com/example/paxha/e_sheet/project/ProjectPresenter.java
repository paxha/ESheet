package com.example.paxha.e_sheet.project;

import com.example.paxha.e_sheet.db.DatabaseHelper;

public interface ProjectPresenter {

    void onCreateProject(String projectName, DatabaseHelper db);

    void onUpdateProject(int id, String projectName, DatabaseHelper db);

    void onDestroy();

}
