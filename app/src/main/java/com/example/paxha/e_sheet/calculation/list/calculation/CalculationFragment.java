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
import com.example.paxha.e_sheet.calculation.create.calculation.CreateCalculationFragment;
import com.example.paxha.e_sheet.calculation.edit.calculation.EditCalculationFragment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CalculationFragment extends Fragment {

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

        calculationModels = new ArrayList<>();

        calculationModels.add(new CalculationModel(5, "add", "description", 2, 2, 5, 2, 2, 12));
        calculationModels.add(new CalculationModel(15, "add", "abc..", 2, 2, 5, 2, 2, 12));
        calculationModels.add(new CalculationModel(51, "add", "some", 2, 2, 5, 2, 2, 12));
        calculationModels.add(new CalculationModel(55, "add", "description", 2, 2, 5, 2, 2, 12));
        calculationModels.add(new CalculationModel(23, "sub", "sdj", 2, 2, 5, 2, 2, 12));
        calculationModels.add(new CalculationModel(58, "add", "rott  kcd eeeee kafi aa.", 2, 2, 5, 2, 2, 12));
        calculationModels.add(new CalculationModel(32, "sub", "fj ckdi  nvcl", 2, 2, 5, 2, 2, 12));
        calculationModels.add(new CalculationModel(47, "add", "description", 2, 2, 5, 2, 2, 12));
        calculationModels.add(new CalculationModel(99, "add", "description", 2, 2, 5, 2, 2, 12));

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
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.constraint_layout, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });


        return view;
    }

}
