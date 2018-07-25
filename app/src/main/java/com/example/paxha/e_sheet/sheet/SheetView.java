package com.example.paxha.e_sheet.sheet;

public interface SheetView {

    void showProgress();

    void hideProgress();

    void setSheetNameError(String message);

    void navigateToNext();

    void showError(String message);

}
