package com.tavanhieu.fastfood.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.tavanhieu.fastfood.R;
import com.tavanhieu.fastfood.my_class.Categories;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolderCategory> {
    private ArrayList<Categories> arr;

    @SuppressLint("NotifyDataSetChanged")
    public CategoryAdapter(ArrayList<Categories> arr) {
        this.arr = arr;
        notifyDataSetChanged();
    }

    public static class ViewHolderCategory extends RecyclerView.ViewHolder {
        private LinearLayout layoutItem;
        private ImageView imgItem;
        private TextView txtItem;

        public ViewHolderCategory(@NonNull View itemView) {
            super(itemView);

            layoutItem = itemView.findViewById(R.id.layout_item_category_main);
            imgItem = itemView.findViewById(R.id.img_item_category_main);
            txtItem = itemView.findViewById(R.id.txt_item_category_main);
        }
    }

    @NonNull
    @Override
    public ViewHolderCategory onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mViews = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new ViewHolderCategory(mViews);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCategory holder, int position) {
        Categories item = arr.get(position);
        if (item == null)
            return;
        holder.imgItem.setImageResource(item.getImg());
        holder.txtItem.setText(item.getTitle());
        switch (position) {
            case 0: {
                holder.layoutItem.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.cat_background1));
                break;
            }
            case 1: {
                holder.layoutItem.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.cat_background2));
                break;
            }
            case 2: {
                holder.layoutItem.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.cat_background3));
                break;
            }
            case 3: {
                holder.layoutItem.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.cat_background4));
                break;
            }
            case 4: {
                holder.layoutItem.setBackground(ContextCompat.getDrawable(holder.imgItem.getContext(), R.drawable.cat_background5));
                break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }
}
