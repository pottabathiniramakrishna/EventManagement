package com.inforica.booker.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inforica.booker.R;

/**
 * Created by ranjith on 01-05-2016.
 */
public class CalenderAgenda extends Fragment {
    View rootView;
    /**
     * http://vainavisolutions.com/clalenders.json
     * Holds the network connection status.
     */
    private boolean network_status = false;

    private RecyclerView calenderRecyclerView;
    LinearLayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;

    public CalenderAgenda() {
        // Required empty public constructor
    }

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);

    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        rootView = inflater.inflate (R.layout.calender_agenda_recyclerview, container, false);
//        calenderRecyclerView = (RecyclerView) rootView.findViewById(R.id.calender_recycler_list);
        mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
     //   calenderRecyclerView.setLayoutManager(mLayoutManager);
        return rootView;
    }



    @Override
    public void onResume () {
        super.onResume ();
    }
}