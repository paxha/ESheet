package com.example.paxha.e_sheet.project.create.project;

public interface CreateProjectView {

    void showProgress();
    void hideProgress();
    void setProjectNameError(String message);
    void navigateToNext();
    void showError(String message);

}
