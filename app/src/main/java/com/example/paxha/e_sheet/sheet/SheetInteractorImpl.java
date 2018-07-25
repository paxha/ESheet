package com.example.paxha.e_sheet.sheet;

import android.text.TextUtils;
import android.util.Log;

import com.example.paxha.e_sheet.db.DatabaseHelper;

import java.util.regex.Pattern;

public class SheetInteractorImpl implements SheetInteractor {
    @Override
    public void createSheet(int projectId, String sheetName, DatabaseHelper db, onFinishListener listener) {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9!@#$%^&*()? ]*");
        if (TextUtils.isEmpty(sheetName))
            listener.onSheetNameError("Sheet name is empty");
        else if (!pattern.matcher(sheetName).matches())
            listener.onSheetNameError("Invalid name");
        else {
            SheetModel sheetModel = new SheetModel();
            sheetModel.setName(sheetName);
            int r = db.createSheet(sheetModel, projectId);
            if (r > 0)
                listener.onSuccess();
            else
                listener.onFailure("Something went wrong");
        }
    }

    @Override
    public void updateSheet(int id, String sheetName, DatabaseHelper db, onFinishListener listener) {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9!@#$%^&*()? ]*");
        if (TextUtils.isEmpty(sheetName))
            listener.onSheetNameError("Sheet name is empty");
        else if (!pattern.matcher(sheetName).matches())
            listener.onSheetNameError("Invalid name");
        else {
            SheetModel sheetModel = new SheetModel();
            sheetModel.setId(id);
            sheetModel.setName(sheetName);
            int r = db.updateSheet(sheetModel);
            if (r > 0)
                listener.onSuccess();
            else
                listener.onFailure("Something went wrong");
        }
    }
}
