package com.example.paxha.e_sheet.project.list.project;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import com.example.paxha.e_sheet.project.create.project.CreateProjectFragment;
import com.example.paxha.e_sheet.project.edit.project.EditProjectFragment;
import com.example.paxha.e_sheet.sheet.list.sheet.SheetFragment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectFragment extends Fragment {

    ArrayList<ProjectModel> projectModels;
    ListView lvProjectList;


    public ProjectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_project, container, false);

        lvProjectList = view.findViewById(R.id.lv_project_list);

        projectModels = new ArrayList<>();

        projectModels.add(new ProjectModel(5, "Android Project"));
        projectModels.add(new ProjectModel(6, "Google Project"));
        projectModels.add(new ProjectModel(16, "Microsoft Project"));
        projectModels.add(new ProjectModel(31, "ESheet Project"));
        projectModels.add(new ProjectModel(12, "Other Project"));

        ProjectAdapter projectAdapter = new ProjectAdapter(getContext(), projectModels);

        lvProjectList.setAdapter(projectAdapter);

        lvProjectList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                SheetFragment fragment = new SheetFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.constraint_layout, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        lvProjectList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                final ProjectModel projectModel = projectModels.get(position);
                PopupMenu popup = new PopupMenu(getContext(), view);
                popup.getMenuInflater().inflate(R.menu.menu_main, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getTitle().toString()) {
                            case "Edit":
                                EditProjectFragment fragment = new EditProjectFragment();
                                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                transaction.replace(R.id.constraint_layout, fragment);
                                transaction.addToBackStack(null);
                                transaction.commit();
                                break;
                            case "Delete":
                                new AlertDialog.Builder(getContext())
                                        .setTitle("Alert")
                                        .setMessage("Do you really want to delete this project?")
                                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                Toast.makeText(getContext(), "Deleting this project by ID = " + projectModel.getId(), Toast.LENGTH_SHORT).show();
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
                CreateProjectFragment fragment = new CreateProjectFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.constraint_layout, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }

}
