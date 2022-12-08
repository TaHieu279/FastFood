package com.tavanhieu.fastfood.activities.purchase_order;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tavanhieu.fastfood.R;
import com.tavanhieu.fastfood.adapters.ToPayAdapter;
import com.tavanhieu.fastfood.adapters.ToShipAdapter;
import com.tavanhieu.fastfood.my_class.Payment;

import java.util.ArrayList;
import java.util.Objects;

public class ItemPurchaseOrderActivity extends AppCompatActivity {
    private ImageView imgBack;
    private TextView txtTitle;
    private RecyclerView rcvDanhSach;

    private ArrayList<Payment> arr = new ArrayList<>();
    private ToPayAdapter adapter1;
    private ToShipAdapter adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_purchase_order);

        String type = getIntent().getStringExtra("type");
        anhXa();
        myOnClick();
        txtTitle.setText(type);
        //Ánh xạ adapter:
        adapter1 = new ToPayAdapter(this, arr);
        adapter2 = new ToShipAdapter(this, arr);
        if(type.equals("ToPay")) {
            rcvDanhSach.setAdapter(adapter1);
        } else {
            rcvDanhSach.setAdapter(adapter2);
        }
        //Lấy dữ liệu từ firebase:
        loadData(type);
    }

    private void myOnClick() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void loadData(String type) {
        FirebaseDatabase
                .getInstance()
                .getReference()
                .child("TablePayment")
                .child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
                .addValueEventListener(new ValueEventListener() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot data: snapshot.getChildren()) {
                            Payment payment = data.getValue(Payment.class);
                            if(payment != null && payment.getStatus().equals(type)) {
                                arr.add(payment);
                            }
                        }
                        adapter1.notifyDataSetChanged();
                        adapter2.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
    }

    private void anhXa() {
        imgBack = findViewById(R.id.img_back);
        txtTitle = findViewById(R.id.txt_title_item_purchase_order);
        rcvDanhSach = findViewById(R.id.rcv_item_purchase_order);
    }
}