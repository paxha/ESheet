package com.example.paxha.e_sheet.calculation.list.calculation;

import android.annotation.SuppressLint;
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

public class CalculationAdapter extends ArrayAdapter<CalculationModel> {

    private ArrayList<CalculationModel> calculationModels;
    private Context context;

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
        this.context = context;
    }

    private int lastPosition = -1;

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        CalculationModel calculationModel = calculationModels.get(position);
        ViewHolder viewHolder;

        final View view;

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

            view = convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            view = convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(context, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        view.startAnimation(animation);

        lastPosition = position;

        viewHolder.tvDescription.setText(calculationModel.getDescription());
        viewHolder.tvWidth.setText(calculationModel.getWidthFeet() + "\' " + calculationModel.getWidthInches() + "\"");
        viewHolder.tvHeight.setText(calculationModel.getHeightFeet() + "\' " + calculationModel.getHeightInches() + "\"");
        viewHolder.tvQuantity.setText(calculationModel.getQuantity() + "");
        viewHolder.tvTotal.setText(calculationModel.getTotal() + "");
        viewHolder.tvType.setText(calculationModel.getType());

        return convertView;
    }
}
