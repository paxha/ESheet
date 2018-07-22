package com.example.paxha.e_sheet.calculation.list.calculation;

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
import com.example.paxha.e_sheet.calculation.CalculationModel;

import java.util.ArrayList;

public class CalculationAdapter extends ArrayAdapter<CalculationModel> {

    private ArrayList<CalculationModel> calculationModels;

    private static class ViewHolder {
        TextView tvDescription;
        TextView tvWidth;
        TextView tvHeight;
        TextView tvQuantity;
        TextView tvTotal;
        TextView tvType;
    }


    CalculationAdapter(@NonNull Context context, ArrayList<CalculationModel> calculationModels) {
        super(context, R.layout.lv_calculation_list_layout, calculationModels);
        this.calculationModels = calculationModels;
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        CalculationModel calculationModel = calculationModels.get(position);
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new CalculationAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.lv_calculation_list_layout, parent, false);

            viewHolder.tvDescription = convertView.findViewById(R.id.tv_description);
            viewHolder.tvHeight = convertView.findViewById(R.id.tv_height);
            viewHolder.tvQuantity = convertView.findViewById(R.id.tv_quantity);
            viewHolder.tvTotal = convertView.findViewById(R.id.tv_total);
            viewHolder.tvType = convertView.findViewById(R.id.tv_type);
            viewHolder.tvWidth = convertView.findViewById(R.id.tv_width);

            convertView.setTag(viewHolder);
        } else
            viewHolder = (ViewHolder) convertView.getTag();

        viewHolder.tvDescription.setText(calculationModel.getDescription());
        viewHolder.tvWidth.setText(calculationModel.getWidthFeet() + "\' " + calculationModel.getWidthInches() + "\"");
        viewHolder.tvHeight.setText(calculationModel.getHeightFeet() + "\' " + calculationModel.getHeightInches() + "\"");
        viewHolder.tvQuantity.setText(calculationModel.getQuantity() + "");
        viewHolder.tvTotal.setText(calculationModel.getTotalFeet() + "\' " + calculationModel.getTotalInches() + "\"");
        viewHolder.tvType.setText(calculationModel.getType());

        return convertView;
    }
}
