package com.example.paxha.e_sheet.calculation;

import com.example.paxha.e_sheet.db.DatabaseHelper;

public class CalculationPresenterImpl implements CalculationPresenter, CalculationInteractor.onFinishListener {

    private CalculationView view;
    private CalculationInteractor interactor;

    public CalculationPresenterImpl(CalculationView view) {
        this.view = view;
        interactor = new CalculationInteractorImpl();
    }

    @Override
    public void onCreateCalculation(int sheetId, String description, String widthFeet, String widthInches, String heightFeet, String heightInches, String quantity, String type, DatabaseHelper db) {
        view.showProgress();
        interactor.createCalculation(sheetId, description, widthFeet, widthInches, heightFeet, heightInches, quantity, type, db, this);
    }

    @Override
    public void onUpdateCalculation(int id, String description, String widthFeet, String widthInches, String heightFeet, String heightInches, String quantity, String type, DatabaseHelper db) {
        view.showProgress();
        interactor.updateCalculation(id, description, widthFeet, widthInches, heightFeet, heightInches, quantity, type, db, this);
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void onDescriptionError(String message) {
        view.hideProgress();
        view.setDescriptionError(message);
    }

    @Override
    public void onWidthFeetError(String message) {
        view.hideProgress();
        view.setWidthFeetError(message);
    }

    @Override
    public void onWidthInchesError(String message) {
        view.hideProgress();
        view.setWidthInchesError(message);
    }

    @Override
    public void onHeightFeetError(String message) {
        view.hideProgress();
        view.setHeightFeetError(message);
    }

    @Override
    public void onHeightInchesError(String message) {
        view.hideProgress();
        view.setHeightInchesError(message);
    }

    @Override
    public void onQuantityError(String message) {
        view.hideProgress();
        view.setQuantityError(message);
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
