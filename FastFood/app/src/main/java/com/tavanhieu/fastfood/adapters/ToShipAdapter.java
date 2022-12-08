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
import com.tavanhieu.fastfood.activities.purchase_order.StatusToPayActivity;
import com.tavanhieu.fastfood.activities.purchase_order.StatusToShipActivity;
import com.tavanhieu.fastfood.my_class.Payment;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

public class ToShipAdapter extends RecyclerView.Adapter<ToShipAdapter.MyViewHolder>{
    private ArrayList<Payment> arr;
    private Context context;

    @SuppressLint("NotifyDataSetChanged")
    public ToShipAdapter(Context context, ArrayList<Payment> arr) {
        this.context = context;
        this.arr = arr;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_purchase_order, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Payment item = arr.get(position);
        if (item != null) {
            holder.txtId.setText("#id: " +  item.getId());
            holder.txtUserName.setText(item.getUser().getUserName());
            holder.txtAddress.setText(item.getUser().getAddress());
            holder.txtDate.setText(item.getDate().toString());
            holder.txtPrice.setText("$" + new DecimalFormat("0.00").format(item.getTotalPayment()));
            holder.txtNumberArr.setText("(" + item.getArrBuyProduct().size() + " dishes)");

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(holder.itemView.getContext(), StatusToShipActivity.class);
                    intent.putExtra("detail_order", item);
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgItem;
        private TextView txtDate, txtId, txtUserName, txtAddress, txtPrice, txtNumberArr;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.txt_id_item);
            txtDate = itemView.findViewById(R.id.txt_date_item);
            imgItem = itemView.findViewById(R.id.img_item_setting);
            txtPrice = itemView.findViewById(R.id.txt_price_item);
            txtAddress = itemView.findViewById(R.id.txt_address_item);
            txtUserName = itemView.findViewById(R.id.txt_user_name_item);
            txtNumberArr = itemView.findViewById(R.id.txt_number_arr_item);
        }
    }
}