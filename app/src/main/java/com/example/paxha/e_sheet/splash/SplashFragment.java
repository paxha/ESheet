package com.example.paxha.e_sheet.splash;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.paxha.e_sheet.R;
import com.example.paxha.e_sheet.project.list.project.ProjectFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class SplashFragment extends Fragment {


    public SplashFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_splash, container, false);

        long delayedSeconds = (long) 1.5;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ProjectFragment projectFragment = new ProjectFragment();
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.constraint_layout, projectFragment);
                transaction.remove(SplashFragment.this);
                transaction.commit();
            }
        }, (delayedSeconds * 1000));

        return view;
    }

}
