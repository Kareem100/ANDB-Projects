package com.example.luxorguide;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RestaurantsFragment extends Fragment {

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
        customItems.add(new CustomItem(getString(R.string.sahaby_lane_restaurant),
                getString(R.string.sahaby_lane_restaurant_info), R.drawable.restaurant_sahaly_lane));
        customItems.add(new CustomItem(getString(R.string.saha_restaurant),
                getString(R.string.saha_restaurant_info), R.drawable.restaurant_saha));
        customItems.add(new CustomItem(getString(R.string.aisha_restaurant),
                getString(R.string.aisha_restaurant_info), R.drawable.restaurant_aisha));

        ItemsAdapter itemsAdapter = new ItemsAdapter(root.getContext(), customItems);
        recyclerView.setAdapter(itemsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
    }
}