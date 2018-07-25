package com.example.paxha.e_sheet.project;

public interface ProjectView {

    void showProgress();

    void hideProgress();

    void setProjectNameError(String message);

    void navigateToNext();

    void showError(String message);

}
