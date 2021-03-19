package com.example.luxorguide;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EntertainmentsFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<CustomItem> customItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.layout_display_list, container, false);
        makeHooks(root);
        return root;
    }

    private void makeHooks(View root){
        recyclerView = root.findViewById(R.id.items_recycler_view);
        customItems = new ArrayList<>();
        customItems.add(new CustomItem(getString(R.string.hot_air_ballooning),
                getString(R.string.hot_air_ballooning_info), R.drawable.entertainment_hot_air_ballooning));
        customItems.add(new CustomItem(getString(R.string.felucca_ride),
                getString(R.string.felucca_ride_info), R.drawable.entertainment_felucca_ride));
        customItems.add(new CustomItem(getString(R.string.nile_cruise),
                getString(R.string.nile_cruise_info), R.drawable.entertainment_nile_cruise));

        ItemsAdapter itemsAdapter = new ItemsAdapter(root.getContext(), customItems);
        recyclerView.setAdapter(itemsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
    }
}