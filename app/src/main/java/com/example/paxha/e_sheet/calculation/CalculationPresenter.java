package com.example.paxha.e_sheet.calculation;

import com.example.paxha.e_sheet.db.DatabaseHelper;

public interface CalculationPresenter {

    void onCreateCalculation(int sheetId, String description, String widthFeet, String widthInches, String heightFeet, String heightInches, String quantity, String type, DatabaseHelper db);

    void onUpdateCalculation(int id, String description, String widthFeet, String widthInches, String heightFeet, String heightInches, String quantity, String type, DatabaseHelper db);

    void onDestroy();

}