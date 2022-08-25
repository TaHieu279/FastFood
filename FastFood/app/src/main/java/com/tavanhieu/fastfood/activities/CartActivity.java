package com.tavanhieu.fastfood.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tavanhieu.fastfood.R;
import com.tavanhieu.fastfood.adapters.CartAdapter;
import com.tavanhieu.fastfood.my_class.BuyProduct;
import com.tavanhieu.fastfood.sqlite_roomdb.MyDatabase;
import com.tavanhieu.fastfood.view_model.MyViewModel;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Observer;

public class CartActivity extends AppCompatActivity {
    private RecyclerView.Adapter<CartAdapter.ViewHolderCart> adapter;
    private MyViewModel myViewModel;
    private RecyclerView rcvCart;
    private Button btnPayment;
    private TextView txtItemTotal, txtTax, txtDeliveryServices, txtTotal;

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

                //hiển thị tổng tiền của các sản phẩm:
                float sum = 0;
                for (BuyProduct item : buyProducts) {
                    sum += item.getPrice() * item.getNumber();
                }

                //Tính tổng tiền, giá thuế,...
                //Nếu danh sách sản phẩm mua bị xóa hết -> thì sẽ set về $0.0
                if (buyProducts.size() > 0) {
                    float tax = sum * 0.02f;
                    DecimalFormat df = new DecimalFormat("0.00");
                    txtTax.setText("$" + df.format(tax));
                    txtDeliveryServices.setText("$10.0");
                    txtItemTotal.setText("$" + df.format(sum));
                    txtTotal.setText("$" + df.format(sum + 10 + tax));
                } else {
                    txtTax.setText("$0.0");
                    txtDeliveryServices.setText("$0.0");
                    txtItemTotal.setText("$0.0");
                    txtTotal.setText("$0.0");
                }
            }
        });
    }

    private void anhXa() {
        rcvCart = findViewById(R.id.rcv_item_cart);
        btnPayment = findViewById(R.id.btn_payment_cart);
        txtItemTotal = findViewById(R.id.txt_itemTotal_cart);
        txtTax = findViewById(R.id.txt_tax_cart);
        txtDeliveryServices = findViewById(R.id.txt_deliveryService_cart);
        txtTotal = findViewById(R.id.txt_total_cart);
    }
}