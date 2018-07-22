package com.example.paxha.e_sheet.calculation.create.calculation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.paxha.e_sheet.R;
import com.example.paxha.e_sheet.calculation.CalculationModel;
import com.example.paxha.e_sheet.db.DatabaseHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateCalculationFragment extends Fragment {

    DatabaseHelper db;

    public CreateCalculationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_calculation, container, false);

        db = new DatabaseHelper(getContext());

        final EditText etDescription = view.findViewById(R.id.et_description);
        final EditText etWidthFeet = view.findViewById(R.id.et_width_feet);
        final EditText etWidthInches = view.findViewById(R.id.et_width_inches);
        final EditText etHeightFeet = view.findViewById(R.id.et_height_feet);
        final EditText etHeightInches = view.findViewById(R.id.et_height_inches);
        final EditText etQuantity = view.findViewById(R.id.et_quantity);
        final Spinner spinnerType = view.findViewById(R.id.spinner_type);
        Button buttonCreateCalculation = view.findViewById(R.id.button_create_calculation);

        buttonCreateCalculation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalculationModel calculationModel = new CalculationModel();
                calculationModel.setType(spinnerType.getSelectedItem().toString().trim());
                calculationModel.setDescription(etDescription.getText().toString().trim());
                calculationModel.setWidthFeet(Integer.parseInt(etWidthFeet.getText().toString().trim()));
                calculationModel.setWidthInches((Integer.parseInt(etWidthInches.getText().toString().trim())));
                calculationModel.setHeightFeet(Integer.parseInt(etHeightFeet.getText().toString().trim()));
                calculationModel.setHeightInches(Integer.parseInt(etHeightInches.getText().toString().trim()));
                calculationModel.setQuantity(Integer.parseInt(etQuantity.getText().toString().trim()));
                calculationModel.setTotalFeet(getTotal("feet", Integer.parseInt(etWidthFeet.getText().toString().trim()), Integer.parseInt(etWidthInches.getText().toString().trim()), Integer.parseInt(etHeightFeet.getText().toString().trim()), Integer.parseInt(etHeightInches.getText().toString().trim()), Integer.parseInt(etQuantity.getText().toString().trim())));
                calculationModel.setTotalInches(getTotal("inches", Integer.parseInt(etWidthFeet.getText().toString().trim()), Integer.parseInt(etWidthInches.getText().toString().trim()), Integer.parseInt(etHeightFeet.getText().toString().trim()), Integer.parseInt(etHeightInches.getText().toString().trim()), Integer.parseInt(etQuantity.getText().toString().trim())));

                db.createCalculation(calculationModel, getArguments().getInt("KEY_SHEET_ID"));
                getFragmentManager().popBackStackImmediate();
            }
        });

        return view;
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
