package com.tavanhieu.fastfood.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tavanhieu.fastfood.R;
import com.tavanhieu.fastfood.my_class.User;

import java.util.Objects;

public class UserInformationActivity extends AppCompatActivity {
    private ImageView imgBack;
    private Button btnSettingInformation;
    private EditText edtUserName, edtDateOfBirth, edtAddress;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);

        anhXa();
        myOnClick();
        getUserFromFirebase();
    }

    private void getUserFromFirebase() {
        //Lấy ra uid:
        String uid = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        //Lấy ra user từ uid:
        FirebaseDatabase
                .getInstance()
                .getReference()
                .child("TableUser")
                .child(uid)
                .addValueEventListener(new ValueEventListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        try {
                            //Lấy user từ Firebase:
                            User tam = snapshot.getValue(User.class);
                            //Gán dữ liệu ra text:
                            if (tam != null) {
                                edtUserName.setText(tam.getUserName());
                                edtDateOfBirth.setText(tam.getDateOfBirth());
                                edtAddress.setText(tam.getAddress());
                                user = tam;
                            }
                        } catch (Exception ex) {
                            Toast.makeText(UserInformationActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) { }
                });

    }

    private void myOnClick() {
        btnSettingInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserInformationActivity.this, SettingInformationActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void anhXa() {
        btnSettingInformation = findViewById(R.id.btn_setting_information);
        edtUserName = findViewById(R.id.edt_user_name);
        edtDateOfBirth = findViewById(R.id.edt_date_of_birth);
        edtAddress = findViewById(R.id.edt_address);
        imgBack = findViewById(R.id.img_back);
    }
}