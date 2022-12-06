package com.tavanhieu.fastfood.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.tavanhieu.fastfood.R;
import com.tavanhieu.fastfood.my_class.BuyProduct;
import com.tavanhieu.fastfood.my_class.ItemCategories;
import com.tavanhieu.fastfood.sqlite_roomdb.MyDatabase;

import java.util.List;

public class ShowDetailActivity extends AppCompatActivity {
    private TextView txtTitle, txtDescription, txtPrice, txtNumber, txtBuyNow;
    private ImageView imgItem, imgMinus, imgPlus, imgBack;
    ItemCategories object;
    private Integer numberOfItem = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        anhXa();
        loadInformationItem();
        myOnClick();
    }

    private void myOnClick() {
        //giảm số lượng mua:
        imgMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(numberOfItem > 1)
                    numberOfItem--;
                txtNumber.setText(String.valueOf(numberOfItem));
            }
        });

        //tăng số lượng mua:
        imgPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberOfItem++;
                txtNumber.setText(String.valueOf(numberOfItem));
            }
        });

        //thêm vào danh sách mua:
        txtBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Tạo ra đối tượng cần mua để thêm vào CSDL
                BuyProduct item = new BuyProduct(null, object.getName(), object.getImage(), numberOfItem, object.getPrice());
                //Lấy ra danh sách sản phẩm cần mua
                List<BuyProduct> arr = MyDatabase.getMyInstances(ShowDetailActivity.this).myDao().getList();
                //Kiểm tra SP đã có trong danh sách hay chưa?
                //Nếu có thì cập nhật, ngược lại thì thêm mới
                boolean exitAlready = false;
                for(int i = 0; i < arr.size(); i++) {
                    if(arr.get(i).getTitle().equals(item.getTitle())) {
                        exitAlready = true;
                    }
                }
                if(exitAlready) {
                    MyDatabase.getMyInstances(ShowDetailActivity.this).myDao().updateByTitle(item.getNumber(), item.getTitle());
                    Toast.makeText(ShowDetailActivity.this, "Update product to cart!", Toast.LENGTH_SHORT).show();
                } else {
                    MyDatabase.getMyInstances(ShowDetailActivity.this).myDao().insert(item);
                    Toast.makeText(ShowDetailActivity.this, "Insert product to cart!", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void loadInformationItem() {
        //Lấy thông tin của món ăn từ RecycleView:
        object = (ItemCategories) getIntent().getSerializableExtra("object");
        if(object != null) {
            txtTitle.setText(String.valueOf(object.getName()));
//            txtDescription.setText(String.valueOf(object.getDescription()));
            txtPrice.setText("$" + object.getPrice());
            Picasso.get().load(object.getImage()).into(imgItem);
        }
    }

    private void anhXa() {
        txtTitle        = findViewById(R.id.txt_title_showDetail);
        txtDescription  = findViewById(R.id.txt_description_showDetail);
        txtPrice        = findViewById(R.id.txt_price_showDetail);
        txtNumber       = findViewById(R.id.txt_number_showDetail);
        txtBuyNow       = findViewById(R.id.txt_buyNow_showDetail);
        imgItem         = findViewById(R.id.img_item_showDetail);
        imgMinus        = findViewById(R.id.img_minus_showDetail);
        imgPlus         = findViewById(R.id.img_plus_showDetail);
        imgBack         = findViewById(R.id.img_back);
    }
}