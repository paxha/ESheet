package com.example.paxha.e_sheet.calculation;

import com.example.paxha.e_sheet.db.DatabaseHelper;

public interface CalculationInteractor {

    interface onFinishListener {

        void onDescriptionError(String message);

        void onWidthFeetError(String message);

        void onWidthInchesError(String message);

        void onHeightFeetError(String message);

        void onHeightInchesError(String message);

        void onQuantityError(String message);

        void onSuccess();

        void onFailure(String message);

    }

    void createCalculation(int sheetId, String description, String widthFeet, String widthInches, String heightFeet, String heightInches, String quantity, String type, DatabaseHelper db, onFinishListener listener);

    void updateCalculation(int id, String description, String widthFeet, String widthInches, String heightFeet, String heightInches, String quantity, String type, DatabaseHelper db, onFinishListener listener);

}
