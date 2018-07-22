package com.example.paxha.e_sheet.sheet.list.sheet;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.paxha.e_sheet.R;
import com.example.paxha.e_sheet.sheet.SheetModel;

import java.util.ArrayList;

public class SheetAdapter extends ArrayAdapter<SheetModel> {

    private ArrayList<SheetModel> sheetModels;

    private static class ViewHolder {
        TextView tvSheetName;
    }

    SheetAdapter(@NonNull Context context, ArrayList<SheetModel> sheetModels) {
        super(context, R.layout.lv_sheet_list_layout, sheetModels);
        this.sheetModels = sheetModels;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        SheetModel sheetModel = sheetModels.get(position);
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.lv_sheet_list_layout, parent, false);
            viewHolder.tvSheetName = convertView.findViewById(R.id.tv_sheet_name);

            convertView.setTag(viewHolder);
        } else
            viewHolder = (ViewHolder) convertView.getTag();

        viewHolder.tvSheetName.setText(sheetModel.getName());

        return convertView;
    }
}
