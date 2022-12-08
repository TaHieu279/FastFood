package com.tavanhieu.fastfood.activities.purchase_order;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;
import com.tavanhieu.fastfood.R;
import com.tavanhieu.fastfood.adapters.ListProductOrderAdapter;
import com.tavanhieu.fastfood.my_class.Payment;

import java.text.DecimalFormat;

public class StatusToShipActivity extends AppCompatActivity {
    private ImageView imgBack;
    private EditText edtAddress;
    private TextView txtId, txtUserName, txtPhoneNumber, txtDate, txtItemTotal, txtShipping, txtTotalPayment, txtStatus;
    private RecyclerView rcvList;

    private ListProductOrderAdapter adapter;
    private Payment item;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_to_ship);

        anhXa();

        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        item = (Payment) getIntent().getSerializableExtra("detail_order");
        if (item != null) {
            edtAddress.setText(item.getUser().getAddress());
            txtId.setText(item.getId());
            txtDate.setText(item.getDate().toString());
            txtUserName.setText(item.getUser().getUserName());
            txtPhoneNumber.setText(item.getUser().getPhoneNumber());
            txtItemTotal.setText(decimalFormat.format(item.getItemTotal()));
            txtShipping.setText(decimalFormat.format(item.getShipping()));
            txtTotalPayment.setText(decimalFormat.format(item.getTotalPayment()));
            txtStatus.setText(item.getStatus());
            //ánh xạ list adapter
            adapter = new ListProductOrderAdapter(item.getArrBuyProduct());
            rcvList.setAdapter(adapter);
        }

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void updateStatus() {
        FirebaseDatabase
                .getInstance()
                .getReference()
                .child("TablePayment")
                .child(item.getUser().getUid())
                .child(item.getId())
                .child("status")
                .setValue("Cancel");
        Toast.makeText(this, "Cancelled this order.", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void anhXa() {
        imgBack = findViewById(R.id.img_back);
        rcvList = findViewById(R.id.rcv_list);
        txtId = findViewById(R.id.txt_id);
        txtDate = findViewById(R.id.txt_date);
        txtUserName = findViewById(R.id.txt_user);
        txtPhoneNumber = findViewById(R.id.txt_phone);
        txtItemTotal = findViewById(R.id.txt_item_total);
        txtShipping = findViewById(R.id.txt_shipping);
        txtTotalPayment = findViewById(R.id.txt_total_payment);
        txtStatus = findViewById(R.id.txt_status);
        edtAddress = findViewById(R.id.edt_address);
    }
}