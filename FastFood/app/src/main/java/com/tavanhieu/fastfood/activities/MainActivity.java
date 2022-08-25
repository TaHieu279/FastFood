package com.tavanhieu.fastfood.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tavanhieu.fastfood.R;
import com.tavanhieu.fastfood.adapters.CategoryAdapter;
import com.tavanhieu.fastfood.adapters.PopularAdapter;
import com.tavanhieu.fastfood.my_class.BuyProduct;
import com.tavanhieu.fastfood.my_class.Categories;
import com.tavanhieu.fastfood.my_class.Popular;
import com.tavanhieu.fastfood.sqlite_roomdb.MyDatabase;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rcvCategory;
    private RecyclerView rcvPopular;
    private ArrayList<Categories> arr1;
    private ArrayList<Popular> arr2;
    private FloatingActionButton btnCart;
    private TextView txtHome;
    private ImageView imgHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();
        deployCategoryAdapter();
        deployPopularAdapter();
        myOnClick();

        txtHome.setTextColor(Color.parseColor("#ff5e00"));
        imgHome.setImageResource(R.drawable.ic_home_orange);
    }

    private void anhXa() {
        rcvCategory = findViewById(R.id.rcv_categories_main);
        rcvPopular = findViewById(R.id.rcv_popular_main);
        btnCart = findViewById(R.id.float_bar_main);
        txtHome = findViewById(R.id.txt_home_main);
        imgHome = findViewById(R.id.img_home_main);
    }

    private void deployCategoryAdapter() {
        //Thêm dữ liệu cho mảng Catgory:
        arr1 = new ArrayList<>();
        arr1.add(new Categories(R.drawable.cat_1, "Pizza"));
        arr1.add(new Categories(R.drawable.cat_2, "Cake"));
        arr1.add(new Categories(R.drawable.cat_3, "Hot dog"));
        arr1.add(new Categories(R.drawable.cat_4, "Coca"));
        arr1.add(new Categories(R.drawable.cat_5, "Pie"));
        //Ánh xạ adapter category:
        RecyclerView.Adapter<CategoryAdapter.ViewHolderCategory> adapter = new CategoryAdapter(arr1);
        rcvCategory.setAdapter(adapter);
    }

    private void deployPopularAdapter() {
        //Thêm dữ liệu cho mảng Popular:
        arr2 = new ArrayList<>();
        arr2.add(new Popular("Pepperoni pizza", "This is description for item popular in fast food app." +
                "This is description for item popular in fast food app 1." +
                "This is description for item popular in fast food app 2." +
                "This is description for item popular in fast food app 3." +
                "This is description for item popular in fast food app 4." +
                "This is description for item popular in fast food app 5." +
                "This is description for item popular in fast food app 6." +
                "This is description for item popular in fast food app 7.", R.drawable.pop_1, 9.76f));
        arr2.add(new Popular("Cheese Burger", "This is description for item popular in fast food app." +
                "This is description for item popular in fast food app 1." +
                "This is description for item popular in fast food app 2." +
                "This is description for item popular in fast food app 3.", R.drawable.pop_2, 8.5f));
        arr2.add(new Popular("Vegetable pizza", "This is description for item popular in fast food app.\n" +
                "This is description for item popular in fast food app 1.\n" +
                "This is description for item popular in fast food app 2.\n" +
                "This is description for item popular in fast food app 3.\n" +
                "This is description for item popular in fast food app 4.\n" +
                "This is description for item popular in fast food app 5.\n" +
                "This is description for item popular in fast food app 6.\n" +
                "This is description for item popular in fast food app 7.", R.drawable.pop_3,  10.8f));
        //Ánh xạ adapter popular:
        RecyclerView.Adapter<PopularAdapter.ViewHolderPopular> adapter = new PopularAdapter(arr2);
        rcvPopular.setAdapter(adapter);
    }

    private void myOnClick() {
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CartActivity.class));
            }
        });
    }
}