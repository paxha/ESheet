package com.example.paxha.e_sheet.sheet.edit.sheet;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.paxha.e_sheet.R;
import com.example.paxha.e_sheet.db.DatabaseHelper;
import com.example.paxha.e_sheet.sheet.SheetModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditSheetFragment extends Fragment {

    DatabaseHelper db;

    public EditSheetFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_sheet, container, false);

        db = new DatabaseHelper(getContext());
        SheetModel sheetModel = db.getSheet(getArguments().getInt("KEY_SHEET_ID"));

        final EditText etSheetName = view.findViewById(R.id.et_sheet_name);
        etSheetName.setText(sheetModel.getName());

        Button buttonUpdateSheet = view.findViewById(R.id.button_update_sheet);
        buttonUpdateSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SheetModel sheetModel = new SheetModel();
                sheetModel.setId(getArguments().getInt("KEY_SHEET_ID"));
                sheetModel.setName(etSheetName.getText().toString().trim());
                db.updateSheet(sheetModel);
                getFragmentManager().popBackStackImmediate();
            }
        });

        return view;
    }

}
