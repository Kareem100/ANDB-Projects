package com.example.luxorguide;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MuseumsFragment extends Fragment {

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
        customItems.add(new CustomItem(getString(R.string.luxor_museum),
                getString(R.string.luxor_museum_info), R.drawable.museum_luxor));
        customItems.add(new CustomItem(getString(R.string.karnak_museum),
                getString(R.string.karnak_museum_info), R.drawable.museum_karnak));
        customItems.add(new CustomItem(getString(R.string.mummification_museum),
                getString(R.string.mummification_museum_info), R.drawable.museum_mummification));
        customItems.add(new CustomItem(getString(R.string.nobbles_art_gallery_museum),
                getString(R.string.nobbles_art_gallery_museum_info), R.drawable.museum_nobles_art_gallery));

        ItemsAdapter itemsAdapter = new ItemsAdapter(root.getContext(), customItems);
        recyclerView.setAdapter(itemsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
    }
}