package com.example.paxha.e_sheet.sheet;

import com.example.paxha.e_sheet.db.DatabaseHelper;

public interface SheetInteractor {

    interface onFinishListener {

        void onSheetNameError(String message);

        void onSuccess();

        void onFailure(String message);

    }

    void createSheet(int projectId, String sheetName, DatabaseHelper db, onFinishListener listener);

    void updateSheet(int id, String sheetName, DatabaseHelper db, onFinishListener listener);

}
