package com.tavanhieu.fastfood.fragment_login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tavanhieu.fastfood.R;
import com.tavanhieu.fastfood.activities.MainActivity;

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
                startActivity(new Intent(requireActivity(), MainActivity.class));
                requireActivity().finish();
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
