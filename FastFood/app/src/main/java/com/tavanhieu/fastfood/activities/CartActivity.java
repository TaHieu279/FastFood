package com.tavanhieu.fastfood.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tavanhieu.fastfood.R;
import com.tavanhieu.fastfood.adapters.CartAdapter;
import com.tavanhieu.fastfood.my_class.BuyProduct;
import com.tavanhieu.fastfood.view_model.MyViewModel;

import java.text.DecimalFormat;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    private RecyclerView.Adapter<CartAdapter.ViewHolderCart> adapter;
    private MyViewModel myViewModel;
    private RecyclerView rcvCart;
    private Button btnPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        anhXa();

        //ViewModel: lắng nghe thay đổi dữ liệu trên database và cập nhật sự thay đổi lên giao diện
        myViewModel = new ViewModelProvider(CartActivity.this).get(MyViewModel.class);
        myViewModel.getListForLiveData(CartActivity.this).observe(CartActivity.this, new androidx.lifecycle.Observer<List<BuyProduct>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(List<BuyProduct> buyProducts) { //hàm lắng nghe sự thay đổi của dữ liệu
                adapter = new CartAdapter(buyProducts);
                rcvCart.setAdapter(adapter);
            }
        });

        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this, CheckoutActivity.class));
            }
        });
    }

    private void anhXa() {
        rcvCart = findViewById(R.id.rcv_item_cart);
        btnPayment = findViewById(R.id.btn_payment_cart);
    }
}