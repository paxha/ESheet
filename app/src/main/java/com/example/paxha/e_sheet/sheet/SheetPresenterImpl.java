package com.example.paxha.e_sheet.sheet;

import com.example.paxha.e_sheet.db.DatabaseHelper;

public class SheetPresenterImpl implements SheetPresenter, SheetInteractor.onFinishListener {

    private SheetView view;
    private SheetInteractor interactor;

    public SheetPresenterImpl(SheetView view) {
        this.view = view;
        interactor = new SheetInteractorImpl();
    }

    @Override
    public void onCreateSheet(int projectId, String sheetName, DatabaseHelper db) {
        view.showProgress();
        interactor.createSheet(projectId, sheetName, db, this);
    }

    @Override
    public void onUpdateSheet(int id, String sheetName, DatabaseHelper db) {
        view.showProgress();
        interactor.updateSheet(id, sheetName, db, this);
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void onSheetNameError(String message) {
        view.hideProgress();
        view.setSheetNameError(message);
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
