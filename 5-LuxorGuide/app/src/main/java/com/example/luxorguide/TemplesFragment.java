package com.example.luxorguide;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class TemplesFragment extends Fragment {

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
        customItems.add(new CustomItem(getString(R.string.luxor_temple),
                getString(R.string.luxor_temple_info), R.drawable.temple_luxor));
        customItems.add(new CustomItem(getString(R.string.karnak_temple),
                getString(R.string.karnak_temple_info), R.drawable.temple_karnak));
        customItems.add(new CustomItem(getString(R.string.dair_bahri_temple),
                getString(R.string.dair_bahri_temple_info), R.drawable.temple_aldair_albahri));
        customItems.add(new CustomItem(getString(R.string.kom_ombo_temple),
                getString(R.string.kom_ombo_temple_info), R.drawable.temple_kom_ombo));
        customItems.add(new CustomItem(getString(R.string.mortuary_temple),
                getString(R.string.luxor_temple_info), R.drawable.temple_horas));

        ItemsAdapter itemsAdapter = new ItemsAdapter(root.getContext(), customItems);
        recyclerView.setAdapter(itemsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
    }
}