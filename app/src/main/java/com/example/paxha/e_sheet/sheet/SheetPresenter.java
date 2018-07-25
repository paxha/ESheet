package com.example.paxha.e_sheet.sheet;

import com.example.paxha.e_sheet.db.DatabaseHelper;

public interface SheetPresenter {

    void onCreateSheet(int projectId, String sheetName, DatabaseHelper db);

    void onUpdateSheet(int id, String sheetName, DatabaseHelper db);

    void onDestroy();

}
