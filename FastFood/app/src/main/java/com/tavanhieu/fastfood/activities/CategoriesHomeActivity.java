package com.tavanhieu.fastfood.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tavanhieu.fastfood.R;
import com.tavanhieu.fastfood.adapters.PopularAdapter;
import com.tavanhieu.fastfood.my_class.ItemCategories;

import java.util.ArrayList;

public class CategoriesHomeActivity extends AppCompatActivity {
    private TextView txtTitle;
    private ImageView imgBack;
    private RecyclerView rcvDanhSach;
    private int position;

    private ArrayList<ItemCategories> arr = new ArrayList<>();
    private PopularAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_home);

        anhXa();

        position = getIntent().getIntExtra("item", -1);
        openListItemCategories(position);

        //Ánh xạ adapter
        adapter = new PopularAdapter(arr);
        rcvDanhSach.setAdapter(adapter);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void anhXa() {
        imgBack = findViewById(R.id.img_back);
        txtTitle = findViewById(R.id.txt_title_categories_home);
        rcvDanhSach = findViewById(R.id.rcv_categories_home);
    }

    private void openListItemCategories(int position) {
        if (position == -1) {
            Toast.makeText(this, "Not found list!", Toast.LENGTH_SHORT).show();
            return;
        }
        switch (position) {
            case 0: {
                loadList("Pizza");
                break;
            }
            case 1: {
                loadList("Bugger");
                break;
            }
            case 2: {
                loadList("HotDog");
                break;
            }
            case 3: {
                loadList("Drink");
                break;
            }
            case 4: {
                loadList("Pie");
                break;
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private void loadList(String tableName) {
        txtTitle.setText("List " + tableName);
        //Lấy danh sách:
        FirebaseDatabase
                .getInstance()
                .getReference()
                .child("Table" + tableName)
                .addValueEventListener(new ValueEventListener() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        arr.clear();
                        //Load dữ liệu từ firebase
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            ItemCategories itemCategories = dataSnapshot.getValue(ItemCategories.class);
                            arr.add(itemCategories);
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
    }
}