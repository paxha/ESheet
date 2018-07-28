package com.example.paxha.e_sheet.sheet.create.sheet;


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
import android.widget.Toast;

import com.example.paxha.e_sheet.R;
import com.example.paxha.e_sheet.db.DatabaseHelper;
import com.example.paxha.e_sheet.sheet.SheetPresenter;
import com.example.paxha.e_sheet.sheet.SheetPresenterImpl;
import com.example.paxha.e_sheet.sheet.SheetView;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateSheetFragment extends Fragment implements SheetView {

    DatabaseHelper db;
    EditText etSheetName;
    ProgressBar progressBar;
    SheetPresenter presenter;

    public CreateSheetFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_sheet, container, false);

        db = new DatabaseHelper(getContext());

        etSheetName = view.findViewById(R.id.et_sheet_name);
        progressBar = view.findViewById(R.id.progress_bar);

        Button buttonCreateSheet = view.findViewById(R.id.button_create_sheet);
        buttonCreateSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter = new SheetPresenterImpl(CreateSheetFragment.this);
                presenter.onCreateSheet(getArguments().getInt("KEY_PROJECT_ID"), etSheetName.getText().toString().trim(), db);
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
    public void setSheetNameError(String message) {
        etSheetName.setError(message);
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
}
