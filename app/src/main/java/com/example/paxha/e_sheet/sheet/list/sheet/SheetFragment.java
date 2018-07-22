package com.example.paxha.e_sheet.sheet.list.sheet;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.paxha.e_sheet.R;
import com.example.paxha.e_sheet.calculation.list.calculation.CalculationFragment;
import com.example.paxha.e_sheet.db.DatabaseHelper;
import com.example.paxha.e_sheet.sheet.SheetModel;
import com.example.paxha.e_sheet.sheet.create.sheet.CreateSheetFragment;
import com.example.paxha.e_sheet.sheet.edit.sheet.EditSheetFragment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SheetFragment extends Fragment {

    DatabaseHelper db;
    ArrayList<SheetModel> sheetModels;
    ListView lvSheetList;

    public SheetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sheet, container, false);

        lvSheetList = view.findViewById(R.id.lv_sheet_list);

        db = new DatabaseHelper(getContext());
        sheetModels = db.getAllSheets(getArguments().getInt("KEY_PROJECT_ID"));

        SheetAdapter adapter = new SheetAdapter(getContext(), sheetModels);
        lvSheetList.setAdapter(adapter);

        lvSheetList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                final SheetModel sheetModel = sheetModels.get(position);
                Bundle bundle = new Bundle();
                bundle.putInt("KEY_SHEET_ID", sheetModel.getId());
                CalculationFragment fragment = new CalculationFragment();
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.constraint_layout, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        lvSheetList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                final SheetModel sheetModel = sheetModels.get(position);
                PopupMenu popup = new PopupMenu(getContext(), view);
                popup.getMenuInflater().inflate(R.menu.menu_main, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getTitle().toString()) {
                            case "Edit":
                                EditSheetFragment fragment = new EditSheetFragment();
                                Bundle bundle = new Bundle();
                                bundle.putInt("KEY_SHEET_ID", sheetModel.getId());
                                fragment.setArguments(bundle);
                                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                transaction.replace(R.id.constraint_layout, fragment);
                                transaction.addToBackStack(null);
                                transaction.commit();
                                break;
                            case "Delete":
                                new AlertDialog.Builder(getContext())
                                        .setTitle("Alert")
                                        .setMessage("Do you really want to delete this sheet?")
                                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                db.deleteSheet(sheetModel.getId());
                                                getFragmentManager().beginTransaction().detach(SheetFragment.this).attach(SheetFragment.this).commit();
                                            }
                                        })
                                        .show();
                                break;
                        }
                        return true;
                    }
                });
                popup.show();
                return true;
            }
        });

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("KEY_PROJECT_ID", getArguments().getInt("KEY_PROJECT_ID"));
                CreateSheetFragment fragment = new CreateSheetFragment();
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.constraint_layout, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }

}
