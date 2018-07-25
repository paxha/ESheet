package com.example.paxha.e_sheet.project;

import com.example.paxha.e_sheet.db.DatabaseHelper;

public class ProjectPresenterImpl implements ProjectPresenter, ProjectInteractor.onFinishListener {

    private ProjectView view;
    private ProjectInteractor interactor;

    public ProjectPresenterImpl(ProjectView view) {
        this.view = view;
        interactor = new ProjectInteractorImpl();
    }

    @Override
    public void onCreateProject(String projectName, DatabaseHelper db) {
        view.showProgress();
        interactor.createProject(projectName, db, this);
    }

    @Override
    public void onUpdateProject(int id, String projectName, DatabaseHelper db) {
        view.showProgress();
        interactor.updateProject(id, projectName, db, this);
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void onProjectNameError(String message) {
        view.hideProgress();
        view.setProjectNameError(message);
    }

    @Override
    public void onSuccess() {
        view.hideProgress();
        view.navigateToNext();
    }

    @Override
    public void onFailure(String message) {
        view.hideProgress();
        view.showError(message);
    }
}
