package com.example.paxha.e_sheet.project.create.project;

import com.example.paxha.e_sheet.db.DatabaseHelper;

public interface CreateProjectPresenter {

    void onValidate(String projectName, DatabaseHelper db);
    void onDestroy();

}
