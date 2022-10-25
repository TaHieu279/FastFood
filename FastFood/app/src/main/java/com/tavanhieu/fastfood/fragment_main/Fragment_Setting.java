package com.tavanhieu.fastfood.fragment_main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tavanhieu.fastfood.R;
import com.tavanhieu.fastfood.activities.LoginActivity;
import com.tavanhieu.fastfood.activities.SettingInformationActivity;
import com.tavanhieu.fastfood.my_class.User;

import java.util.Objects;

public class Fragment_Setting extends Fragment {
    private View mView;
    private Button btnSettingInformation, btnLogOut;
    private EditText edtUserName, edtDateOfBirth, edtAddress;
    private User user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_personal_informaltion, container, false);

        anhXa();
        myOnClick();
        getUserFromFirebase();

        return mView;
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
                            Toast.makeText(requireContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) { }
                });

    }

    private void myOnClick() {
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Đăng xuất khỏi ứng dụng:
                startActivity(new Intent(requireActivity(), LoginActivity.class));
                FirebaseAuth.getInstance().signOut();
                requireActivity().finish();
            }
        });

        btnSettingInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireActivity(), SettingInformationActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }

    private void anhXa() {
        btnLogOut = mView.findViewById(R.id.btn_log_out);
        btnSettingInformation = mView.findViewById(R.id.btn_setting_information);
        edtUserName = mView.findViewById(R.id.edt_user_name);
        edtDateOfBirth = mView.findViewById(R.id.edt_date_of_birth);
        edtAddress = mView.findViewById(R.id.edt_address);
    }
}
