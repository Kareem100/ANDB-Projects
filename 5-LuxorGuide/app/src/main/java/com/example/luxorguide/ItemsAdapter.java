package com.example.luxorguide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    private Context context;
    private ArrayList<CustomItem> customItems;

    public ItemsAdapter(Context context, ArrayList<CustomItem> customItems) {
        this.context = context;
        this.customItems = customItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.layout_item,
                parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setTitle(customItems.get(position).getTitle());
        holder.setInfo(customItems.get(position).getInfo());
        holder.setImageResId(customItems.get(position).getImageResId());
    }

    @Override
    public int getItemCount() {
        return customItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView info;
        private ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.text_title_item);
            info = itemView.findViewById(R.id.text_info_item);
            image = itemView.findViewById(R.id.image_item);
        }

        public void setTitle(String title) {
            this.title.setText(title);
        }

        public void setInfo(String info) {
            this.info.setText(info);
        }

        public void setImageResId(int imageResId) {
            this.image.setImageResource(imageResId);
        }
    }
}
