package com.tavanhieu.fastfood.fragment_main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.tavanhieu.fastfood.R;
import com.tavanhieu.fastfood.activities.LoginActivity;
import com.tavanhieu.fastfood.activities.user_info.UserInformationActivity;
import com.tavanhieu.fastfood.adapters.MyWalletAdapter;
import com.tavanhieu.fastfood.adapters.PurchaseOrderAdapter;
import com.tavanhieu.fastfood.my_class.Categories;

import java.util.ArrayList;

public class Fragment_Setting extends Fragment {
    private View mView;
    private Button btnPersonalInfor, btnHelp, btnLogout;
    private RecyclerView rcvPurchaseOrder, rcvMyWallet;
    private TextView txtUserName;

    //Sử dụng lại Categories để không phải tạo class mới.
    private ArrayList<Categories> arr1 = new ArrayList<>(), arr2 = new ArrayList<>();
    private PurchaseOrderAdapter adapter1;
    private MyWalletAdapter adapter2;
    private SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_setting, container, false);

        //Khởi tạo sharedPreferences để lưu trữ thông tin người dùng:
        sharedPreferences = requireActivity().getSharedPreferences("InformationUser", Context.MODE_PRIVATE);

        anhXa();
        myOnClick();
        loadData();

        return mView;
    }

    private void loadData() {
        //Hiển thị tên người dùng:
        txtUserName.setText(sharedPreferences.getString("UserName", ""));
        //Thêm mảng:
        addArrayForSetting();
    }

    private void addArrayForSetting() {
        //Purchase Order
        arr1.add(new Categories(R.drawable.to_pay, "To Pay"));
        arr1.add(new Categories(R.drawable.to_ship, "To Ship"));
        arr1.add(new Categories(R.drawable.to_receive, "To Receive"));
        arr1.add(new Categories(R.drawable.cancel, "Cancel"));
        adapter1 = new PurchaseOrderAdapter(requireActivity(), arr1);
        rcvPurchaseOrder.setAdapter(adapter1);
        //My Wallet
        arr2.add(new Categories(R.drawable.wallet, "Wallet"));
        arr2.add(new Categories(R.drawable.voucher, "Vouchers"));
        arr2.add(new Categories(R.drawable.coin, "My coins"));
        adapter2 = new MyWalletAdapter(arr2);
        rcvMyWallet.setAdapter(adapter2);
    }

    private void myOnClick() {
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Logout tài khoản
                FirebaseAuth.getInstance().signOut();
                //Trở về màn hình login
                startActivity(new Intent(requireActivity(), LoginActivity.class));
                requireActivity().finish();
            }
        });

        btnPersonalInfor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireActivity(), UserInformationActivity.class));
            }
        });
    }

    private void anhXa() {
        txtUserName = mView.findViewById(R.id.txt_user_name);
        btnHelp = mView.findViewById(R.id.btn_help);
        btnLogout = mView.findViewById(R.id.btn_logout);
        btnPersonalInfor = mView.findViewById(R.id.btn_personal_infor);
        rcvMyWallet = mView.findViewById(R.id.rcv_my_wallet);
        rcvPurchaseOrder = mView.findViewById(R.id.rcv_purchase_order);
    }
}
