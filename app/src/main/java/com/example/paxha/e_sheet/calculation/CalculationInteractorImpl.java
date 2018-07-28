package com.example.paxha.e_sheet.calculation;

import android.text.TextUtils;

import com.example.paxha.e_sheet.db.DatabaseHelper;

public class CalculationInteractorImpl implements CalculationInteractor {
    @Override
    public void createCalculation(int sheetId, String description, String widthFeet, String widthInches, String heightFeet, String heightInches, String quantity, String type, DatabaseHelper db, onFinishListener listener) {

        if (TextUtils.isEmpty(widthFeet))
            listener.onWidthFeetError("Empty width feet");
        else if (Integer.parseInt(widthFeet) < 1)
            listener.onWidthFeetError("Invalid feet");
        else if (TextUtils.isEmpty(widthInches))
            listener.onWidthInchesError("Empty width inches");
        else if (Integer.parseInt(widthInches) < 1 && Integer.parseInt(widthInches) > 11)
            listener.onWidthInchesError("Invalid inches");
        else if (TextUtils.isEmpty(heightFeet))
            listener.onHeightFeetError("Empty height feet");
        else if (Integer.parseInt(heightFeet) < 1)
            listener.onHeightFeetError("Invalid feet");
        else if (TextUtils.isEmpty(heightInches))
            listener.onWidthInchesError("Empty height inches");
        else if (Integer.parseInt(heightInches) < 1 && Integer.parseInt(heightInches) > 11)
            listener.onHeightInchesError("Invalid inches");
        else if (TextUtils.isEmpty(quantity))
            listener.onWidthInchesError("Empty quantity");
        else {
            CalculationModel calculationModel = new CalculationModel();
            calculationModel.setSheet_id(sheetId);
            calculationModel.setDescription(description);
            calculationModel.setWidthFeet(Integer.parseInt(widthFeet));
            calculationModel.setWidthInches(Integer.parseInt(widthInches));
            calculationModel.setHeightFeet(Integer.parseInt(heightFeet));
            calculationModel.setHeightInches(Integer.parseInt(heightInches));
            calculationModel.setQuantity(Integer.parseInt(quantity));
            calculationModel.setType(type);
            calculationModel.setTotalFeet(getTotal("feet", Integer.parseInt(widthFeet), Integer.parseInt(widthInches), Integer.parseInt(heightFeet), Integer.parseInt(heightInches), Integer.parseInt(quantity)));
            calculationModel.setTotalInches(getTotal("inches", Integer.parseInt(widthFeet), Integer.parseInt(widthInches), Integer.parseInt(heightFeet), Integer.parseInt(heightInches), Integer.parseInt(quantity)));

            int r = db.createCalculation(calculationModel, sheetId);
            if (r > 0)
                listener.onSuccess();
            else
                listener.onFailure("Something went wrong");
        }
    }

    @Override
    public void updateCalculation(int id, String description, String widthFeet, String widthInches, String heightFeet, String heightInches, String quantity, String type, DatabaseHelper db, onFinishListener listener) {
        if (TextUtils.isEmpty(widthFeet))
            listener.onWidthFeetError("Empty width feet");
        else if (TextUtils.isEmpty(widthInches))
            listener.onWidthInchesError("Empty width inches");
        else if (TextUtils.isEmpty(heightFeet))
            listener.onHeightFeetError("Empty height feet");
        else if (TextUtils.isEmpty(heightInches))
            listener.onWidthInchesError("Empty height inches");
        else if (TextUtils.isEmpty(quantity))
            listener.onWidthInchesError("Empty quantity");
        else {
            CalculationModel calculationModel = new CalculationModel();
            calculationModel.setId(id);
            calculationModel.setDescription(description);
            calculationModel.setWidthFeet(Integer.parseInt(widthFeet));
            calculationModel.setWidthInches(Integer.parseInt(widthInches));
            calculationModel.setHeightFeet(Integer.parseInt(heightFeet));
            calculationModel.setHeightInches(Integer.parseInt(heightInches));
            calculationModel.setQuantity(Integer.parseInt(quantity));
            calculationModel.setType(type);
            calculationModel.setTotalFeet(getTotal("feet", Integer.parseInt(widthFeet), Integer.parseInt(widthInches), Integer.parseInt(heightFeet), Integer.parseInt(heightInches), Integer.parseInt(quantity)));
            calculationModel.setTotalInches(getTotal("inches", Integer.parseInt(widthFeet), Integer.parseInt(widthInches), Integer.parseInt(heightFeet), Integer.parseInt(heightInches), Integer.parseInt(quantity)));

            int r = db.updateCalculation(calculationModel);
            if (r > 0)
                listener.onSuccess();
            else
                listener.onFailure("Something went wrong");
        }
    }

    private int getTotal(String what, int widthFeet, int widthInches, int heightFeet, int heightInches, int quantity) {
        int totalFeet = 0;
        int totalInches;
        totalInches = (((widthFeet * heightFeet) * 12) + (widthFeet * heightInches) + (heightFeet * widthInches)) * quantity;
        while (totalInches > 11) {
            totalFeet++;
            totalInches -= 12;
        }
        if (what.equals("feet"))
            return totalFeet;
        else
            return totalInches;
    }
}
