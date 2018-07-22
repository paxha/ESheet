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
import android.widget.Toast;

import com.example.paxha.e_sheet.R;
import com.example.paxha.e_sheet.calculation.list.calculation.CalculationFragment;
import com.example.paxha.e_sheet.sheet.create.sheet.CreateSheetFragment;
import com.example.paxha.e_sheet.sheet.edit.sheet.EditSheetFragment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SheetFragment extends Fragment {

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

        sheetModels = new ArrayList<>();
        sheetModels.add(new SheetModel(21, "Sheet no 1"));
        sheetModels.add(new SheetModel(11, "abc 1"));
        sheetModels.add(new SheetModel(291, "Sheet abc"));
        sheetModels.add(new SheetModel(22, "Sheet rck k"));
        sheetModels.add(new SheetModel(255, "unknowns"));
        sheetModels.add(new SheetModel(111, "top c"));
        sheetModels.add(new SheetModel(126, "left right"));
        sheetModels.add(new SheetModel(563, "end a"));
        sheetModels.add(new SheetModel(74, "aspenct"));

        SheetAdapter adapter = new SheetAdapter(getContext(), sheetModels);
        lvSheetList.setAdapter(adapter);

        lvSheetList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                CalculationFragment fragment = new CalculationFragment();
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
                                                Toast.makeText(getContext(), "Deleting this sheet by ID = " + sheetModel.getId(), Toast.LENGTH_SHORT).show();
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
                CreateSheetFragment fragment = new CreateSheetFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.constraint_layout, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }

}
