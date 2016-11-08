package com.csnewengineeringbuilding.floor;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.csnewengineeringbuilding.R;

/**
 * Created by dn2757 on 2016. 11. 2..
 */

public class SecondFloorFragment extends Fragment {

    public static Fragment newInstance(int floor) {
        SecondFloorFragment fragment = new SecondFloorFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("floor", floor);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second_floor, container, false);
        initToolbar(view);
        return view;
    }

    private void initToolbar(View view) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setTitle(getFloor() + "층");
    }

    private int getFloor() {
        return getArguments().getInt("floor");
    }
}
