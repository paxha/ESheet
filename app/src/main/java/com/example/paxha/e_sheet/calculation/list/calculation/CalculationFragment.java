package com.example.paxha.e_sheet.calculation.list.calculation;


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
import com.example.paxha.e_sheet.calculation.CalculationModel;
import com.example.paxha.e_sheet.calculation.create.calculation.CreateCalculationFragment;
import com.example.paxha.e_sheet.calculation.edit.calculation.EditCalculationFragment;
import com.example.paxha.e_sheet.db.DatabaseHelper;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CalculationFragment extends Fragment {

    DatabaseHelper db;
    ArrayList<CalculationModel> calculationModels;
    ListView lvCalculationList;

    public CalculationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calculation, container, false);

        lvCalculationList = view.findViewById(R.id.lv_calculation_list);

        db = new DatabaseHelper(getContext());
        calculationModels = db.getAllCalculations(getArguments().getInt("KEY_SHEET_ID"));

        CalculationAdapter adapter = new CalculationAdapter(getContext(), calculationModels);

        lvCalculationList.setAdapter(adapter);

        lvCalculationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                final CalculationModel calculationModel = calculationModels.get(position);
                Toast.makeText(getContext(), "ID = " + calculationModel.getId(), Toast.LENGTH_SHORT).show();
            }
        });

        lvCalculationList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                final CalculationModel calculationModel = calculationModels.get(position);
                PopupMenu popup = new PopupMenu(getContext(), view);
                popup.getMenuInflater().inflate(R.menu.menu_main, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getTitle().toString()) {
                            case "Edit":
                                EditCalculationFragment fragment = new EditCalculationFragment();
                                Bundle bundle = new Bundle();
                                bundle.putInt("KEY_SHEET_ID", getArguments().getInt("KEY_SHEET_ID"));
                                fragment.setArguments(bundle);
                                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                transaction.replace(R.id.constraint_layout, fragment);
                                transaction.addToBackStack(null);
                                transaction.commit();
                                break;
                            case "Delete":
                                new AlertDialog.Builder(getContext())
                                        .setTitle("Alert")
                                        .setMessage("Do you really want to delete this calculation?")
                                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                Toast.makeText(getContext(), "Deleting this calculation by ID = " + calculationModel.getId(), Toast.LENGTH_SHORT).show();
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
                CreateCalculationFragment fragment = new CreateCalculationFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("KEY_SHEET_ID", getArguments().getInt("KEY_SHEET_ID"));
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
