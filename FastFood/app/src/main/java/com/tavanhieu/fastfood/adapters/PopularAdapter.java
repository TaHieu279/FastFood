package com.tavanhieu.fastfood.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.tavanhieu.fastfood.R;
import com.tavanhieu.fastfood.activities.ShowDetailActivity;
import com.tavanhieu.fastfood.my_class.Popular;
import java.util.ArrayList;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolderPopular> {
    private ArrayList<Popular> arr;

    @SuppressLint("NotifyDataSetChanged")
    public PopularAdapter(ArrayList<Popular> arr) {
        this.arr = arr;
        notifyDataSetChanged();
    }

    public static class ViewHolderPopular extends RecyclerView.ViewHolder {
        private TextView txtTitle, txtPrice, btnAdd;
        private ImageView imgItem;

        public ViewHolderPopular(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txt_title_popular_main);
            txtPrice = itemView.findViewById(R.id.txt_cost_popular_main);
            btnAdd = itemView.findViewById(R.id.txt_add_popular_main);
            imgItem = itemView.findViewById(R.id.img_item_popular_main);
        }
    }

    @NonNull
    @Override
    public ViewHolderPopular onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_popular, parent, false);
        return new ViewHolderPopular(mView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolderPopular holder, int position) {
        Popular item = arr.get(position);
        if(item == null)    return;
        holder.imgItem.setImageResource(item.getImg());
        holder.txtTitle.setText(item.getTile());
        holder.txtPrice.setText("$"+item.getPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), ShowDetailActivity.class);
                intent.putExtra("object", item);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }
}
