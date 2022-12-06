package com.tavanhieu.fastfood.fragment_login;

import android.os.Bundle;
import android.util.Patterns;
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
import com.google.firebase.database.FirebaseDatabase;
import com.tavanhieu.fastfood.R;
import com.tavanhieu.fastfood.my_class.User;

import java.util.Objects;

public class FragmentSignUp extends Fragment {
    private Button btnSignUp;
    private EditText edtUserNameSignUp, edtPassSignUp, edtPassSignUp2, edtEmail;
    private View mView;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.item_sign_up, container, false);
        
        anhXa();
        myOnClick();
        return mView;
    }

    private void myOnClick() {
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUpUser();
            }
        });
    }

    private void signUpUser() {
        //Kiểm tra các giá trị đầu vào:
        if(edtUserNameSignUp.getText().length() == 0
                || edtEmail.getText().length() == 0
                || edtPassSignUp.getText().length() == 0
                || edtPassSignUp2.getText().length() == 0) {
            Toast.makeText(requireActivity(), "Please enter all blank!", Toast.LENGTH_SHORT).show();
        } else {
            //Lấy dữ liệu từ các ô nhập
            String userName = edtUserNameSignUp.getText().toString().trim();
            String email = edtEmail.getText().toString().trim();
            String pass = edtPassSignUp.getText().toString().trim();
            String pass2 = edtPassSignUp2.getText().toString().trim();

            //Kiểm tra dữ liệu:
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                edtEmail.setFocusable(true);
                edtEmail.setError("Email incorrect!");
            } else if(!pass.equals(pass2)) {
                edtPassSignUp2.setFocusable(true);
                edtPassSignUp2.setError("Pass2 doesn't matches with pass1");
            } else {
                createAccount(userName, email, pass);
            }
        }
    }

    private void createAccount(String name, String email, String pass) {
        FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()) {
                        //Nếu người dùng được tạo thành công
                        //Thì cập nhật thông tin lên RealTimeDB.
                        String uid = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
                        FirebaseDatabase
                                .getInstance()
                                .getReference()
                                .child("TableUser")
                                .child(uid)
                                .setValue(new User(name, email, uid));

                        //Sau khi đăng ký xong thì signOut để đăng nhập:
                        FirebaseAuth.getInstance().signOut();
                        //Quay về màn hình đăng nhập:
                        Toast.makeText(requireActivity(), "Register Successfully!", Toast.LENGTH_SHORT).show();
                        requireActivity()
                                .getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_sign_in_up, new FragmentSignIn())
                                .commit();
                    } else {
                        Toast.makeText(requireActivity(), "Register Failed!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void anhXa() {
        edtUserNameSignUp   = mView.findViewById(R.id.edt_user_name_sign_up);
        edtPassSignUp       = mView.findViewById(R.id.edt_pass_word_sign_up);
        edtPassSignUp2      = mView.findViewById(R.id.edt_pass_word2_sign_up);
        btnSignUp           = mView.findViewById(R.id.btn_sign_up);
        edtEmail            = mView.findViewById(R.id.edt_email_sign_up);
    }
}
