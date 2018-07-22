package com.example.paxha.e_sheet.project.list.project;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.paxha.e_sheet.R;
import com.example.paxha.e_sheet.project.ProjectModel;

import java.util.ArrayList;

public class ProjectAdapter extends ArrayAdapter<ProjectModel> {

    private ArrayList<ProjectModel> projectModels;

    private static class ViewHolder {
        TextView tvProjectName;
    }

    ProjectAdapter(@NonNull Context context, ArrayList<ProjectModel> projectModels) {
        super(context, R.layout.lv_project_list_layout, projectModels);
        this.projectModels = projectModels;
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ProjectModel projectModel = projectModels.get(position);
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.lv_project_list_layout, parent, false);
            viewHolder.tvProjectName = convertView.findViewById(R.id.tv_name);

            convertView.setTag(viewHolder);
        } else
            viewHolder = (ViewHolder) convertView.getTag();

        viewHolder.tvProjectName.setText(projectModel.getName());

        return convertView;
    }
}
