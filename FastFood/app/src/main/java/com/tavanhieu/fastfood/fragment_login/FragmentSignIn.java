package com.tavanhieu.fastfood.fragment_login;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.tavanhieu.fastfood.R;
import com.tavanhieu.fastfood.activities.MainActivity;

public class FragmentSignIn extends Fragment {
    private View mView;
    private TextView txtForgotPass;
    private Button btnSignIn;
    private EditText edtUserNameSignIn, edtPassSignIn;
    private CheckBox cbRememberPass;
    private SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.item_sign_in, container, false);

        anhXa();
        myOnClick();

        //Sử dụng sharedPreferences để lưu account:
        sharedPreferences = requireActivity().getSharedPreferences("MY_SHARED_ACCOUNT", Context.MODE_PRIVATE);
        //Load account nếu có:
        edtUserNameSignIn.setText(sharedPreferences.getString("email", null));
        edtPassSignIn.setText(sharedPreferences.getString("pass", null));
        if (edtUserNameSignIn.getText().length() != 0 && edtPassSignIn.getText().length() != 0) {
            cbRememberPass.setChecked(true);
        }
        //Tự động đăng nhập:
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            startActivity(new Intent(requireActivity(), MainActivity.class));
            requireActivity().finish();
        }

        return mView;
    }

    private void myOnClick() {
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkSignIn();
            }
        });
    }

    @SuppressLint("CommitPrefEdits")
    private void checkSignIn() {
        if (edtUserNameSignIn.getText().length() == 0 || edtPassSignIn.getText().length() == 0) {
            Toast.makeText(requireActivity(), "Please enter all blank!", Toast.LENGTH_SHORT).show();
        } else {
            //Lấy dữ liệu input:
            String userName = edtUserNameSignIn.getText().toString().trim();
            String pass = edtPassSignIn.getText().toString().trim();

            //Kiểm tra đăng nhập:
            FirebaseAuth.getInstance().signInWithEmailAndPassword(userName, pass)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            //Lưu lại account:
                            if (cbRememberPass.isChecked()) {
                                sharedPreferences.edit().putString("email", userName).apply();
                                sharedPreferences.edit().putString("pass", pass).apply();
                            } else {
                                sharedPreferences.edit().clear().apply();
                            }

                            //Chuyển sang màn hình chính của ứng dụng:
                            startActivity(new Intent(requireActivity(), MainActivity.class));
                            requireActivity().finish();
                        } else {
                            Toast.makeText(requireActivity(), "Login Failed!", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    private void anhXa() {
        txtForgotPass = mView.findViewById(R.id.txt_forgot_pass_word);
        edtUserNameSignIn = mView.findViewById(R.id.edt_email_sign_in);
        btnSignIn = mView.findViewById(R.id.btn_sign_in);
        edtPassSignIn = mView.findViewById(R.id.edt_pass_word_sign_in);
        cbRememberPass = mView.findViewById(R.id.cbRememberPass);
    }
}
