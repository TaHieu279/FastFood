package com.tavanhieu.fastfood.fragment_login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tavanhieu.fastfood.R;
import com.tavanhieu.fastfood.activities.MainActivity;

public class FragmentSignIn extends Fragment {
    private View mView;
    private TextView txtForgotPass;
    private Button btnSignIn;
    private EditText edtUserNameSignIn, edtPassSignIn;
    private CheckBox cbRememberPass;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.item_sign_in, container, false);

        anhXa();
        myOnClick();

        return mView;
    }

    private void myOnClick() {
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(requireActivity(), MainActivity.class));
                requireActivity().finish();
            }
        });
    }

    private void anhXa() {
        txtForgotPass = mView.findViewById(R.id.txt_forgot_pass_word);
        edtUserNameSignIn = mView.findViewById(R.id.edt_email_sign_in);
        btnSignIn = mView.findViewById(R.id.btn_sign_in);
        edtPassSignIn = mView.findViewById(R.id.edt_pass_word_sign_in);
        cbRememberPass = mView.findViewById(R.id.cbRememberPass);
    }
}
