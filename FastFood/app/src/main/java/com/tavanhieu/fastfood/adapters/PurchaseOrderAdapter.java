package com.tavanhieu.fastfood.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tavanhieu.fastfood.R;
import com.tavanhieu.fastfood.activities.purchase_order.ItemPurchaseOrderActivity;
import com.tavanhieu.fastfood.my_class.Categories;

import java.util.ArrayList;

public class PurchaseOrderAdapter extends RecyclerView.Adapter<PurchaseOrderAdapter.MyViewHolder>{
    private ArrayList<Categories> arr;
    private Context context;

    public PurchaseOrderAdapter(Context context, ArrayList<Categories> arr) {
        this.context = context;
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
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Categories item = arr.get(position);
        if(item != null) {
            holder.txtTitle.setText(item.getTitle());
            holder.imgItem.setImageResource(item.getImg());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Mở màn hình ItemPurchaseOrder hiển thị danh sách đơn hàng theo loại: đã đặt, đang vận chuyển, đã nhận, đã hủy.
                    openItemPurchaseOrder(position);
                }
            });
        }
    }

    private void openItemPurchaseOrder(int position) {
        switch (position) {
            case 0:
                loadItem("ToPay");
                break;
            case 1:
                loadItem("ToShip");
                break;
            case 2:
                loadItem("ToReceive");
                break;
            case 3:
                loadItem("Cancel");
                break;
        }
    }

    private void loadItem(String type) {
        Intent intent = new Intent(context, ItemPurchaseOrderActivity.class);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgItem;
        private TextView txtTitle;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgItem = itemView.findViewById(R.id.img_item_setting);
            txtTitle = itemView.findViewById(R.id.txt_item_setting);
        }
    }
}
