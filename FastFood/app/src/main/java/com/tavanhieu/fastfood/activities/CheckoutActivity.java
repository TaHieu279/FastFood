package com.tavanhieu.fastfood.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;
import com.tavanhieu.fastfood.R;
import com.tavanhieu.fastfood.adapters.CartAdapter;
import com.tavanhieu.fastfood.my_class.BuyProduct;
import com.tavanhieu.fastfood.my_class.Payment;
import com.tavanhieu.fastfood.my_class.User;
import com.tavanhieu.fastfood.view_model.MyViewModel;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;

public class CheckoutActivity extends AppCompatActivity {
    private EditText edtUserName, edtPhoneNumber, edtAddress;
    private TextView txtEdit, txtItemTotal, txtShipping, txtTotalPayment;
    private RecyclerView rcvDanhSach;
    private CartAdapter adapter;
    private Button btnPayment;

    private MyViewModel myViewModel;
    private Payment payment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        anhXa();
        myOnClick();
        loadData();
    }

    private void myOnClick() {
        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payment();
            }
        });

        txtEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDeliveryAddress();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void updateDeliveryAddress() {
        new AlertDialog.Builder(this)
                .setCancelable(false)
                .setView(R.layout.activity_dialog_address)
                .setNegativeButton("Cancel", (dialog, which) -> {
                    dialog.cancel();
                })
                .setPositiveButton("Ok", (dialog, which) -> {
                    EditText name, phone, address;
                    //Ánh xạ:
                    name = ((Dialog) dialog).findViewById(R.id.edt_user_name);
                    phone = ((Dialog) dialog).findViewById(R.id.edt_phone_number);
                    address = ((Dialog) dialog).findViewById(R.id.edt_address);
                    //Set dữ liệu:
                    edtUserName.setText(name.getText().toString().trim());
                    edtAddress.setText(address.getText().toString().trim());
                    edtPhoneNumber.setText("(+84) " + phone.getText().toString().trim());
                })
                .show();
    }

    private void payment() {
        if(payment != null) {
            //Cập nhật sản phẩm mua lên firebase:
            FirebaseDatabase
                    .getInstance()
                    .getReference()
                    .child("TablePayment")
                    .child(payment.getUser().getUid())
                    .child(payment.getDate())
                    .setValue(payment);
            //Xóa bỏ danh sách sản phẩm trong giỏ:
            myViewModel.deleteTable(this);
            Toast.makeText(this, "Payment successful!", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Payment failed!", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadData() {
        //Load thông tin người dùng:
        SharedPreferences sharedPreferences = getSharedPreferences("InformationUser", MODE_PRIVATE);
        edtUserName.setText(sharedPreferences.getString("UserName", null));
//        edtPhoneNumber.setText(sharedPreferences.getString("PhoneNumber", null));
        edtAddress.setText(sharedPreferences.getString("Address", null));

        //ViewModel: lắng nghe thay đổi dữ liệu trên database và cập nhật sự thay đổi lên giao diện
        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);
        myViewModel.getListForLiveData(this).observe(this, new androidx.lifecycle.Observer<List<BuyProduct>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(List<BuyProduct> buyProducts) { //hàm lắng nghe sự thay đổi của dữ liệu
                adapter = new CartAdapter(buyProducts);
                rcvDanhSach.setAdapter(adapter);

                //hiển thị tổng tiền của các sản phẩm:
                float sum = 0;
                for (BuyProduct item : buyProducts) {
                    sum += item.getPrice() * item.getNumber();
                }

                //Tính tổng tiền, giá ship,...
                //Nếu danh sách sản phẩm mua bị xóa hết -> thì sẽ set về $0.00
                float ship = 0;
                if (buyProducts.size() > 0) {
                    ship = sum * 0.05f;
                    DecimalFormat df = new DecimalFormat("0.00");
                    txtShipping.setText("$" + df.format(ship));
                    txtItemTotal.setText("$" + df.format(sum));
                    txtTotalPayment.setText("$" + df.format(sum + ship));
                } else {
                    txtShipping.setText("$0.00");
                    txtItemTotal.setText("$0.00");
                    txtTotalPayment.setText("$0.00");
                }

                //Tạo dữ liệu cho payment để add lên firebase:
                User user = new User(
                        sharedPreferences.getString("Uid", null),
                        sharedPreferences.getString("UserName", null),
                        sharedPreferences.getString("Email", null),
                        sharedPreferences.getString("DateOfBirth", null),
                        sharedPreferences.getString("Address", null),
                        null);
                payment = new Payment(
                        user,
                        buyProducts,
                        sum,
                        ship,
                        sum + ship,
                        Calendar.getInstance().getTime().toString());
            }
        });
    }

    private void anhXa() {
        edtPhoneNumber = findViewById(R.id.txt_phone_number);
        edtUserName = findViewById(R.id.txt_user_name);
        edtAddress = findViewById(R.id.txt_address);
        txtTotalPayment = findViewById(R.id.txt_total_payment);
        txtItemTotal = findViewById(R.id.txt_item_total);
        txtShipping = findViewById(R.id.txt_shipping);
        txtEdit = findViewById(R.id.txt_edit);
        btnPayment = findViewById(R.id.btn_payment);
        rcvDanhSach = findViewById(R.id.rcv_list_checkout);
    }
}