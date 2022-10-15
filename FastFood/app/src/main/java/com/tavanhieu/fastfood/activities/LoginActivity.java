package com.tavanhieu.fastfood.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.tavanhieu.fastfood.R;
import com.tavanhieu.fastfood.fragment_login.FragmentSignIn;
import com.tavanhieu.fastfood.fragment_login.FragmentSignUp;

public class LoginActivity extends AppCompatActivity {
    private TextView btnTabSelected, btnChuyenSignIn, btnChuyenSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Cài đặt form mặc định là SignIn:
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_sign_in_up, new FragmentSignIn()).commit();

        anhXa();
        myOnClick();
    }

    private void myOnClick() {
        btnChuyenSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Chỉnh lại màu text:
                btnChuyenSignIn.setTextColor(Color.WHITE);
                btnChuyenSignUp.setTextColor(Color.BLACK);
                //animate: hiệu ứng di chuyển, x: di chuyển từ 0 tới vị trí x(...), duration: thời gian thực hiện.
                btnTabSelected.animate().x(0).setDuration(100);
                //Chuyển sang form đăng nhập:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_sign_in_up, new FragmentSignIn()).commit();
            }
        });

        btnChuyenSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnChuyenSignIn.setTextColor(Color.BLACK);
                btnChuyenSignUp.setTextColor(Color.WHITE);
                btnTabSelected.animate().x(btnChuyenSignUp.getWidth()).setDuration(100);
                //Chuyển sang form đăng ký:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_sign_in_up, new FragmentSignUp()).commit();
            }
        });
    }

    private void anhXa() {
        btnTabSelected  = findViewById(R.id.btn_tab_selected);
        btnChuyenSignIn = findViewById(R.id.btn_chuyen_sign_in);
        btnChuyenSignUp = findViewById(R.id.btn_chuyen_sign_up);
    }
}