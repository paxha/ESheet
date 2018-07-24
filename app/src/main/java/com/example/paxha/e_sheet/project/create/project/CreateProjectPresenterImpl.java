package com.example.paxha.e_sheet.project.create.project;

import com.example.paxha.e_sheet.db.DatabaseHelper;

public class CreateProjectPresenterImpl implements CreateProjectPresenter, CreateProjectInteractor.onCreateProjectFinishListener {

    private CreateProjectView view;
    private CreateProjectInteractor interactor;

    CreateProjectPresenterImpl(CreateProjectView view) {
        this.view = view;
        interactor = new CreateProjectInteractorImpl();
    }

    @Override
    public void onValidate(String projectName, DatabaseHelper db) {
        interactor.createProject(projectName, db, this);
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
