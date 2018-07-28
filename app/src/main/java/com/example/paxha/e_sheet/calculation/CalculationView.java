package com.example.paxha.e_sheet.calculation;

public interface CalculationView {

    void showProgress();
    void hideProgress();
    void setDescriptionError(String message);
    void setWidthFeetError(String message);
    void setWidthInchesError(String message);
    void setHeightFeetError(String message);
    void setHeightInchesError(String message);
    void setQuantityError(String message);
    void navigateToNext();
    void showError(String message);

}
