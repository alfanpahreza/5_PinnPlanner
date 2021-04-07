package com.example.pinnplanner_v2.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pinnplanner_v2.R;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment{

    private static final String ARG_SECTION_NUMBER = "section_number";
    private ArrayList<task> tasksList;
    private RecyclerView recyclerView;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tasksList = new ArrayList<>();
        setDummyTaskInfo();
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        setAdapter();
        return view;
    }

    private void setAdapter() {
        recyclerAdapter adapter = new recyclerAdapter(tasksList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
    private void setDummyTaskInfo() {
        tasksList.add(new task("Tugas PPB","Tugas mockup dan deskripsi solusi","Due Today","10.00 pm"));
        tasksList.add(new task("Tugas PPL","Deskripsi","Due Tomorrow","10.00 pm"));
        tasksList.add(new task("Tugas StatProb","Deskripsi","Due 3 April 2021","07.00 pm"));
        tasksList.add(new task("Koding tugas PHP","latihan 14","Due 7 April 2021","05.00 pm"));
        tasksList.add(new task("Tugas APSI","Deskripsi","Due 7 April 2021","12.00 pm"));
        tasksList.add(new task("Tugas Pkn","Deskripsi","Due 10 April 2021","07.00 pm"));
        tasksList.add(new task("Tugas PPB","Tugas mockup dan deskripsi solusi","Due Today","10.00 pm"));
        tasksList.add(new task("Tugas PPL","Deskripsi","Due Tomorrow","10.00 pm"));
        tasksList.add(new task("Tugas StatProb","Deskripsi","Due 3 April 2021","07.00 pm"));
    }
}