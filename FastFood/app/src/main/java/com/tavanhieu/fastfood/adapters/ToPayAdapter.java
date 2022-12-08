package com.tavanhieu.fastfood.adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.FirebaseDatabase;
import com.tavanhieu.fastfood.R;
import com.tavanhieu.fastfood.activities.purchase_order.ItemPurchaseOrderActivity;
import com.tavanhieu.fastfood.activities.purchase_order.StatusToPayActivity;
import com.tavanhieu.fastfood.my_class.Payment;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ToPayAdapter extends RecyclerView.Adapter<ToPayAdapter.MyViewHolder>{
    private ArrayList<Payment> arr;
    private Context context;

    public ToPayAdapter(Context context, ArrayList<Payment> arr) {
        this.context = context;
        this.arr = arr;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<Payment> arr) {
        Collections.reverse(arr);
        this.arr = (ArrayList<Payment>) arr;
        //Cập nhật lại sự thay đổi
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_purchase_order_topay, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Payment item = arr.get(position);
        if (item != null) {
            holder.txtId.setText("#id: " + item.getId());
            holder.txtUserName.setText(item.getUser().getUserName());
            holder.txtAddress.setText(item.getUser().getAddress());
            holder.txtDate.setText(item.getDate().toString());
            holder.txtPrice.setText("$" + new DecimalFormat("0.00").format(item.getTotalPayment()));
            holder.txtNumberArr.setText("(" + item.getArrBuyProduct().size() + " dishes)");

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(holder.itemView.getContext(), StatusToPayActivity.class);
                    intent.putExtra("detail_order", item);
                    context.startActivity(intent);
                }
            });

            holder.imgCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(context)
                            .setTitle("Notification")
                            .setMessage("Do you want to cancel this order?")
                            .setNegativeButton("No", (dialog, which) -> {
                            })
                            .setPositiveButton("Yes", (dialog, which) -> {
                                updateStatus(item);
                            }).show();
                }
            });
        }
    }

    private void updateStatus(Payment item) {
        FirebaseDatabase
                .getInstance()
                .getReference()
                .child("TablePayment")
                .child(item.getUser().getUid())
                .child(item.getId())
                .child("status")
                .setValue("Cancel");
        Toast.makeText(context, "Cancelled this order.", Toast.LENGTH_SHORT).show();
        ((ItemPurchaseOrderActivity) context).finish();
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgItem, imgCancel;
        private TextView txtDate, txtId, txtUserName, txtAddress, txtPrice, txtNumberArr;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.txt_id_item);
            txtDate = itemView.findViewById(R.id.txt_date_item);
            imgItem = itemView.findViewById(R.id.img_item_setting);
            txtPrice = itemView.findViewById(R.id.txt_price_item);
            imgCancel = itemView.findViewById(R.id.img_cancel_item);
            txtAddress = itemView.findViewById(R.id.txt_address_item);
            txtUserName = itemView.findViewById(R.id.txt_user_name_item);
            txtNumberArr = itemView.findViewById(R.id.txt_number_arr_item);
        }
    }
}