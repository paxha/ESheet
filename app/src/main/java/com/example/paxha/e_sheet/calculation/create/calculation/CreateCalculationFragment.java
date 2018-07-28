package com.example.paxha.e_sheet.calculation.create.calculation;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.paxha.e_sheet.R;
import com.example.paxha.e_sheet.calculation.CalculationModel;
import com.example.paxha.e_sheet.calculation.CalculationPresenter;
import com.example.paxha.e_sheet.calculation.CalculationPresenterImpl;
import com.example.paxha.e_sheet.calculation.CalculationView;
import com.example.paxha.e_sheet.db.DatabaseHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateCalculationFragment extends Fragment implements CalculationView {

    DatabaseHelper db;
    EditText etDescription;
    EditText etWidthFeet;
    EditText etWidthInches;
    EditText etHeightFeet;
    EditText etHeightInches;
    EditText etQuantity;
    ProgressBar progressBar;

    CalculationPresenter presenter;

    public CreateCalculationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_calculation, container, false);

        db = new DatabaseHelper(getContext());
        presenter = new CalculationPresenterImpl(CreateCalculationFragment.this);

        etDescription = view.findViewById(R.id.et_description);
        etWidthFeet = view.findViewById(R.id.et_width_feet);
        etWidthInches = view.findViewById(R.id.et_width_inches);
        etHeightFeet = view.findViewById(R.id.et_height_feet);
        etHeightInches = view.findViewById(R.id.et_height_inches);
        etQuantity = view.findViewById(R.id.et_quantity);
        progressBar = view.findViewById(R.id.progress_bar);

        final Spinner spinnerType = view.findViewById(R.id.spinner_type);
        Button buttonCreateCalculation = view.findViewById(R.id.button_create_calculation);

        buttonCreateCalculation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onCreateCalculation(getArguments().getInt("KEY_SHEET_ID"), etDescription.getText().toString().trim(), etWidthFeet.getText().toString().trim(), etWidthInches.getText().toString().trim(), etHeightFeet.getText().toString().trim(), etHeightInches.getText().toString().trim(), etQuantity.getText().toString().trim(), spinnerType.getSelectedItem().toString().trim(), db);
            }
        });

        return view;
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setDescriptionError(String message) {
        etDescription.setError(message);
    }

    @Override
    public void setWidthFeetError(String message) {
        etWidthFeet.setError(message);
    }

    @Override
    public void setWidthInchesError(String message) {
        etWidthInches.setError(message);
    }

    @Override
    public void setHeightFeetError(String message) {
        etHeightFeet.setError(message);
    }

    @Override
    public void setHeightInchesError(String message) {
        etHeightInches.setError(message);
    }

    @Override
    public void setQuantityError(String message) {
        etQuantity.setError(message);
    }

    @Override
    public void navigateToNext() {
        hideKeyboard(getContext());
        getFragmentManager().popBackStackImmediate();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    void hideKeyboard(Context context) {
        InputMethodManager manager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        View view = ((Activity) context).getCurrentFocus();
        if (view == null)
            return;
        assert manager != null;
        manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
