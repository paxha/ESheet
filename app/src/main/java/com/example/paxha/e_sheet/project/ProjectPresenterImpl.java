package com.example.paxha.e_sheet.project;

import com.example.paxha.e_sheet.db.DatabaseHelper;

public class ProjectPresenterImpl implements ProjectPresenter, ProjectInteractor.onCreateProjectFinishListener {

    private ProjectView view;
    private ProjectInteractor interactor;

    public ProjectPresenterImpl(ProjectView view) {
        this.view = view;
        interactor = new ProjectInteractorImpl();
    }

    @Override
    public void onCreateProject(String projectName, DatabaseHelper db) {
        interactor.createProject(projectName, db, this);
    }

    @Override
    public void onUpdateProject(int id, String projectName, DatabaseHelper db) {
        interactor.updateProject(id, projectName, db, this);
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void onProjectNameError(String message) {
        if (view != null) {
            view.hideProgress();
            view.setProjectNameError(message);
        }
    }

    @Override
    public void onSuccess() {
        if (view != null) {
            view.hideProgress();
            view.navigateToNext();
        }
    }

    @Override
    public void onFailure(String message) {
        if (view != null) {
            view.hideProgress();
            view.showError(message);
        }
    }
}
