package com.example.paxha.e_sheet.pdf;


import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.paxha.e_sheet.R;
import com.example.paxha.e_sheet.calculation.CalculationModel;
import com.example.paxha.e_sheet.db.DatabaseHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PDFGeneratorFragment extends Fragment {

    DatabaseHelper db;
    ArrayList<CalculationModel> calculationModels;
    ListView lvPDFCalculationList;

    public PDFGeneratorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_pdfgenerator, container, false);

        db = new DatabaseHelper(getContext());
        calculationModels = db.getAllCalculations(getArguments().getInt("KEY_SHEET_ID"));

        PDFGeneratorAdapter adapter = new PDFGeneratorAdapter(getContext(), calculationModels);
        lvPDFCalculationList = view.findViewById(R.id.lv_pdf_calculation_list);
        lvPDFCalculationList.setAdapter(adapter);

        FloatingActionButton fabDownload = view.findViewById(R.id.fab_download);
        fabDownload.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                View view1 = view.findViewById(R.id.pdf_view_layout);
                PdfDocument pdfDocument = new PdfDocument();
                PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(view1.getWidth(), view1.getHeight(), 1).create();
                PdfDocument.Page page = pdfDocument.startPage(pageInfo);
                view1.draw(page.getCanvas());
                pdfDocument.finishPage(page);

                String path = Environment.getExternalStorageDirectory() + "/ESheet";
                File fileDirectory = new File(path);
                File pdfDest = new File(fileDirectory, getArguments().getInt("KEY_SHEET_ID") + "_.pdf");
                try {
                    fileDirectory.mkdirs();
                    pdfDocument.writeTo(new FileOutputStream(pdfDest));
                    Toast.makeText(getContext(), "File saved", Toast.LENGTH_SHORT).show();
                    getFragmentManager().popBackStackImmediate();
                } catch (IOException e) {
                    e.printStackTrace();
                    Snackbar.make(view, e.toString(), Snackbar.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }

}
