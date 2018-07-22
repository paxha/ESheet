package com.example.paxha.e_sheet.sheet.create.sheet;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.SharedElementCallback;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.paxha.e_sheet.R;
import com.example.paxha.e_sheet.db.DatabaseHelper;
import com.example.paxha.e_sheet.sheet.SheetModel;
import com.example.paxha.e_sheet.sheet.list.sheet.SheetFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateSheetFragment extends Fragment {

    DatabaseHelper db;

    public CreateSheetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_sheet, container, false);

        db = new DatabaseHelper(getContext());

        final EditText etSheetName = view.findViewById(R.id.et_sheet_name);
        Button buttonCreateSheet = view.findViewById(R.id.button_create_sheet);
        buttonCreateSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SheetModel sheetModel = new SheetModel();
                sheetModel.setName(etSheetName.getText().toString().trim());
                db.createSheet(sheetModel, getArguments().getInt("KEY_PROJECT_ID"));
                getFragmentManager().popBackStackImmediate();
            }
        });

        return view;
    }

}
