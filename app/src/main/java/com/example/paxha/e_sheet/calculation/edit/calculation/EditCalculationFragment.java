package com.example.paxha.e_sheet.calculation.edit.calculation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
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
public class EditCalculationFragment extends Fragment {

    DatabaseHelper db;

    public EditCalculationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_calculation, container, false);

        db = new DatabaseHelper(getContext());

        final CalculationModel calculationModel = db.getCalculation(getArguments().getInt("KEY_CALCULATION_ID"));


        final EditText etDescription = view.findViewById(R.id.et_description);
        final EditText etWidthFeet = view.findViewById(R.id.et_width_feet);
        final EditText etWidthInches = view.findViewById(R.id.et_width_inches);
        final EditText etHeightFeet = view.findViewById(R.id.et_height_feet);
        final EditText etHeightInches = view.findViewById(R.id.et_height_inches);
        final EditText etQuantity = view.findViewById(R.id.et_quantity);
        final Spinner spinnerType = view.findViewById(R.id.spinner_type);
        Button buttonCreateCalculation = view.findViewById(R.id.button_update_calculation);

        etDescription.setText(calculationModel.getDescription());
        etWidthFeet.setText(String.valueOf(calculationModel.getWidthFeet()));
        etWidthInches.setText(String.valueOf(calculationModel.getWidthInches()));
        etHeightFeet.setText(String.valueOf(calculationModel.getHeightFeet()));
        etHeightInches.setText(String.valueOf(calculationModel.getHeightInches()));
        etQuantity.setText(String.valueOf(calculationModel.getQuantity()));

        buttonCreateCalculation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalculationModel calculationModel1 = new CalculationModel();
                calculationModel1.setId(getArguments().getInt("KEY_CALCULATION_ID"));
                calculationModel1.setType(spinnerType.getSelectedItem().toString().trim());
                calculationModel1.setDescription(etDescription.getText().toString().trim());
                calculationModel1.setWidthFeet(Integer.parseInt(etWidthFeet.getText().toString().trim()));
                calculationModel1.setWidthInches((Integer.parseInt(etWidthInches.getText().toString().trim())));
                calculationModel1.setHeightFeet(Integer.parseInt(etHeightFeet.getText().toString().trim()));
                calculationModel1.setHeightInches(Integer.parseInt(etHeightInches.getText().toString().trim()));
                calculationModel1.setQuantity(Integer.parseInt(etQuantity.getText().toString().trim()));
                calculationModel1.setTotalFeet(getTotal("feet", Integer.parseInt(etWidthFeet.getText().toString().trim()), Integer.parseInt(etWidthInches.getText().toString().trim()), Integer.parseInt(etHeightFeet.getText().toString().trim()), Integer.parseInt(etHeightInches.getText().toString().trim()), Integer.parseInt(etQuantity.getText().toString().trim())));
                calculationModel1.setTotalInches(getTotal("inches", Integer.parseInt(etWidthFeet.getText().toString().trim()), Integer.parseInt(etWidthInches.getText().toString().trim()), Integer.parseInt(etHeightFeet.getText().toString().trim()), Integer.parseInt(etHeightInches.getText().toString().trim()), Integer.parseInt(etQuantity.getText().toString().trim())));

                db.updateCalculation(calculationModel1);
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
