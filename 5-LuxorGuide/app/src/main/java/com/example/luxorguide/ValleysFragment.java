package com.example.luxorguide;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ValleysFragment extends Fragment {

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
        customItems.add(new CustomItem(getString(R.string.valley_kings),
                getString(R.string.valley_kings_info), R.drawable.valley_of_the_kings));
        customItems.add(new CustomItem(getString(R.string.valley_queens),
                getString(R.string.valley_queens_info), R.drawable.valley_of_the_queens));
        customItems.add(new CustomItem(getString(R.string.medinet_habu_valley),
                getString(R.string.medinet_habu_valley_info), R.drawable.valley_madinet_habu));
        customItems.add(new CustomItem(getString(R.string.deir_almadina_valley),
                getString(R.string.deir_almadina_valley_info), R.drawable.valley_deir_al_madina));

        ItemsAdapter itemsAdapter = new ItemsAdapter(root.getContext(), customItems);
        recyclerView.setAdapter(itemsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
    }
}