package com.example.paxha.e_sheet.sheet.list.sheet;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.paxha.e_sheet.R;

import java.util.ArrayList;

public class SheetAdapter extends ArrayAdapter<SheetModel> {

    private ArrayList<SheetModel> sheetModels;
    private Context context;

    private static class ViewHolder {
        TextView tvSheetName;
    }

    SheetAdapter(@NonNull Context context, ArrayList<SheetModel> sheetModels) {
        super(context, R.layout.lv_sheet_list_layout, sheetModels);
        this.sheetModels = sheetModels;
        this.context = context;
    }

    private int lastPosition = -1;

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        SheetModel sheetModel = sheetModels.get(position);
        ViewHolder viewHolder;

        final View view;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.lv_sheet_list_layout, parent, false);
            viewHolder.tvSheetName = convertView.findViewById(R.id.tv_sheet_name);

            view = convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            view = convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(context, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        view.startAnimation(animation);

        lastPosition = position;

        viewHolder.tvSheetName.setText(sheetModel.getName());

        return convertView;
    }
}
