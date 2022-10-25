package com.tavanhieu.fastfood.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.tavanhieu.fastfood.R;
import com.tavanhieu.fastfood.my_class.User;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

public class SettingInformationActivity extends AppCompatActivity {
    private EditText edtUserName, edtDateOfBirth, edtAddress;
    private ImageButton imgDateOfBirth;
    private Button btnCacel, btnUpdate;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_information);

        anhXa();
        //Tải dữ liệu cũ nhập vào các editText:
        loadData();
        //Sự kiện người dùng click vào:
        myOnClick();
    }

    private void loadData() {
        user = (User) getIntent().getSerializableExtra("user");
        if (user != null) {
            edtDateOfBirth.setText(user.getDateOfBirth());
            edtUserName.setText(user.getUserName());
            edtAddress.setText(user.getAddress());
        }
    }

    private void myOnClick() {
        btnCacel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateInformation();
            }
        });

        imgDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Lấy ra ngày tháng năm hiện tại:
                Calendar c = Calendar.getInstance();
                //Dialog date:
                @SuppressLint("SimpleDateFormat")
                DatePickerDialog datePickerDialog = new DatePickerDialog(SettingInformationActivity.this,
                        (datePicker, i, i1, i2) -> {
                            c.set(Calendar.YEAR, i);
                            c.set(Calendar.MONTH, i1);
                            c.set(Calendar.DAY_OF_MONTH, i2);
                            //Đặt ngày tháng được chọn:
                            edtDateOfBirth.setText(new SimpleDateFormat("dd/MM/yyyy").format(c.getTime()));
                        },
                        //Ngày tháng năm mặc định khi hiện dialog:
                        c.get(Calendar.YEAR),
                        c.get(Calendar.MONTH),
                        c.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
    }

    private void updateInformation() {
        if (edtUserName.getText().length() == 0 || edtDateOfBirth.getText().length() == 0 || edtAddress.getText().length() == 0) {
            Toast.makeText(this, "Please enter all blank!", Toast.LENGTH_SHORT).show();
        } else {
            //Tạo user mới:
            User tam = new User(user.getUid(),
                    edtUserName.getText().toString().trim(),
                    user.getEmail(),
                    edtDateOfBirth.getText().toString().trim(),
                    edtAddress.getText().toString().trim(),
                    null);
            //Cập nhật dữ liệu lên firebase
            FirebaseDatabase
                    .getInstance()
                    .getReference()
                    .child("TableUser")
                    .child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
                    .setValue(tam);
            Toast.makeText(this, "Update Successfully!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void anhXa() {
        edtUserName = findViewById(R.id.edt_user_name);
        edtDateOfBirth = findViewById(R.id.edt_date_of_birth);
        edtAddress = findViewById(R.id.edt_address);
        imgDateOfBirth = findViewById(R.id.img_date_of_birth);
        btnCacel = findViewById(R.id.btnCancel);
        btnUpdate = findViewById(R.id.btnUpdate);
    }
}