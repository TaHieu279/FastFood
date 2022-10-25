package com.tavanhieu.fastfood.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tavanhieu.fastfood.R;
import com.tavanhieu.fastfood.fragment_main.Fragment_Contact;
import com.tavanhieu.fastfood.fragment_main.Fragment_Home;
import com.tavanhieu.fastfood.fragment_main.Fragment_Notifies;
import com.tavanhieu.fastfood.fragment_main.Fragment_Setting;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton btnCart;
    private TextView txtHome, txtContact, txtNotifies, txtSetting;
    private ImageView imgHome, imgContact, imgNotifies, imgSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();
        myOnClick();

        //Chọn nút home mặc định
        txtHome.setTextColor(Color.parseColor("#ff5e00"));
        imgHome.setImageResource(R.drawable.ic_home_orange);

        //Cài đặt fragment mặc định:
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_main, new Fragment_Home())
                .commit();
    }

    private void anhXa() {
        //Bottom app bar:
        btnCart = findViewById(R.id.float_bar_main);
        imgHome     = findViewById(R.id.img_home_main);
        txtHome     = findViewById(R.id.txt_home_main);
        imgContact  = findViewById(R.id.img_contact_main);
        txtContact  = findViewById(R.id.txt_contact_main);
        imgNotifies = findViewById(R.id.img_notifies_main);
        txtNotifies = findViewById(R.id.txt_notifies_main);
        imgSetting  = findViewById(R.id.img_setting_main);
        txtSetting  = findViewById(R.id.txt_setting_main);
    }

    private void myOnClick() {
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Chuyển sang activity Cart
                startActivity(new Intent(MainActivity.this, CartActivity.class));
            }
        });

        imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Home is select
                imgHome.setImageResource(R.drawable.ic_home_orange);
                txtHome.setTextColor(Color.parseColor("#ff5e00"));
                imgContact.setImageResource(R.drawable.ic_people);
                txtContact.setTextColor(Color.parseColor("#000000"));
                imgNotifies.setImageResource(R.drawable.ic_notifications);
                txtNotifies.setTextColor(Color.parseColor("#000000"));
                imgSetting.setImageResource(R.drawable.ic_settings);
                txtSetting.setTextColor(Color.parseColor("#000000"));

                //Chuyển sang fragment home:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_main, new Fragment_Home())
                        .commit();
            }
        });

        imgContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgHome.setImageResource(R.drawable.ic_home);
                txtHome.setTextColor(Color.parseColor("#000000"));
                //Contact is select
                imgContact.setImageResource(R.drawable.ic_people_orange);
                txtContact.setTextColor(Color.parseColor("#ff5e00"));
                imgNotifies.setImageResource(R.drawable.ic_notifications);
                txtNotifies.setTextColor(Color.parseColor("#000000"));
                imgSetting.setImageResource(R.drawable.ic_settings);
                txtSetting.setTextColor(Color.parseColor("#000000"));

                //Chuyển sang fragment contact
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_main, new Fragment_Contact())
                        .commit();
            }
        });

        imgNotifies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgHome.setImageResource(R.drawable.ic_home);
                txtHome.setTextColor(Color.parseColor("#000000"));
                imgContact.setImageResource(R.drawable.ic_people);
                txtContact.setTextColor(Color.parseColor("#000000"));
                //Notifies is select
                imgNotifies.setImageResource(R.drawable.ic_notifications_orange);
                txtNotifies.setTextColor(Color.parseColor("#ff5e00"));
                imgSetting.setImageResource(R.drawable.ic_settings);
                txtSetting.setTextColor(Color.parseColor("#000000"));

                //Chuyển sang fragment notification
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_main, new Fragment_Notifies())
                        .commit();
            }
        });

        imgSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgHome.setImageResource(R.drawable.ic_home);
                txtHome.setTextColor(Color.parseColor("#000000"));
                imgContact.setImageResource(R.drawable.ic_people);
                txtContact.setTextColor(Color.parseColor("#000000"));
                imgNotifies.setImageResource(R.drawable.ic_notifications);
                txtNotifies.setTextColor(Color.parseColor("#000000"));
                //Setting is select
                imgSetting.setImageResource(R.drawable.ic_settings_orange);
                txtSetting.setTextColor(Color.parseColor("#ff5e00"));

                //Chuyển sang fragment Setting:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_main, new Fragment_Setting())
                        .commit();
            }
        });
    }
}