package com.tavanhieu.fastfood.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tavanhieu.fastfood.R;
import com.tavanhieu.fastfood.my_class.Categories;

import java.util.ArrayList;

public class PurchaseOrderAdapter extends RecyclerView.Adapter<PurchaseOrderAdapter.MyViewHolder> {
    private ArrayList<Categories> arr;

    public PurchaseOrderAdapter(ArrayList<Categories> arr) {
        this.arr = arr;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @SuppressLint("InflateParams")
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_setting, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Categories item = arr.get(position);
        if(item != null) {
            holder.txtItem.setText(item.getTitle());
            holder.imgItem.setImageResource(item.getImg());
        }
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgItem;
        private TextView txtItem;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgItem = itemView.findViewById(R.id.img_item_setting);
            txtItem = itemView.findViewById(R.id.txt_item_setting);
        }
    }
}
