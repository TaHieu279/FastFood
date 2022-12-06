package com.tavanhieu.fastfood.fragment_main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tavanhieu.fastfood.R;
import com.tavanhieu.fastfood.adapters.CategoryAdapter;
import com.tavanhieu.fastfood.adapters.PopularAdapter;
import com.tavanhieu.fastfood.my_class.Categories;
import com.tavanhieu.fastfood.my_class.ItemCategories;
import com.tavanhieu.fastfood.my_class.User;

import java.util.ArrayList;
import java.util.Objects;

public class Fragment_Home extends Fragment {
    private View mView;
    private RecyclerView rcvCategory, rcvPopular;
    private TextView txtUserName;
    private ArrayList<Categories> arr1;
    private ArrayList<ItemCategories> arr2;
    private RecyclerView.Adapter<PopularAdapter.ViewHolderPopular> adapter;
    private SharedPreferences sharedPreferences;

    @SuppressLint({"SetTextI18n", "ShowToast"})
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home, container, false);

        //Khởi tạo sharedPreferences để lưu trữ thông tin người dùng:
        sharedPreferences = requireActivity().getSharedPreferences("InformationUser", Context.MODE_PRIVATE);

        anhXa();
        getUserFromFirebase();
        getDataCategories();
        getDataPopular();

        txtUserName.setText("Hi, " + sharedPreferences.getString("UserName", ""));
        //Ánh xạ adapter popular:
        adapter = new PopularAdapter(arr2);
        rcvPopular.setAdapter(adapter);
        return mView;
    }

    private void getUserFromFirebase() {
        //Lấy ra uid:
        String uid = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        //Lưu userCurrent vào shared:
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
                            User user = snapshot.getValue(User.class);
                            //Gán dữ liệu ra text:
                            if (user != null) {
                                txtUserName.setText("Hi, " + user.getUserName());
                                //Lưu vào shared
                                sharedPreferences.edit().putString("UserName", user.getUserName()).apply();
                                sharedPreferences.edit().putString("Uid", user.getUid()).apply();
                                sharedPreferences.edit().putString("Email", user.getEmail()).apply();
                                sharedPreferences.edit().putString("Address", user.getAddress()).apply();
                                sharedPreferences.edit().putString("DateOfBirth", user.getDateOfBirth()).apply();
                            }
                        } catch (Exception ex) {
                            Toast.makeText(requireContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
    }

    private void getDataCategories() {
        //Thêm dữ liệu cho mảng Catgory:
        arr1 = new ArrayList<>();
        arr1.add(new Categories(R.drawable.cat_1, "Pizza"));
        arr1.add(new Categories(R.drawable.cat_2, "Bugger"));
        arr1.add(new Categories(R.drawable.cat_3, "Hot dog"));
        arr1.add(new Categories(R.drawable.cat_4, "Drink"));
        arr1.add(new Categories(R.drawable.cat_5, "Pie"));
        //Ánh xạ adapter category:
        RecyclerView.Adapter<CategoryAdapter.ViewHolderCategory> adapter = new CategoryAdapter(requireActivity(), arr1);
        rcvCategory.setAdapter(adapter);
    }

    private void getDataPopular() {
        //Thêm dữ liệu cho mảng Popular:
        arr2 = new ArrayList<>();
        //Lấy danh sách:
        FirebaseDatabase
                .getInstance()
                .getReference()
                .child("TablePopular")
                .addValueEventListener(new ValueEventListener() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        arr2.clear();
                        //Load dữ liệu từ firebase
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            ItemCategories itemCategories = dataSnapshot.getValue(ItemCategories.class);
                            arr2.add(itemCategories);
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
    }

    private void anhXa() {
        rcvCategory = mView.findViewById(R.id.rcv_categories_main);
        rcvPopular = mView.findViewById(R.id.rcv_popular_main);
        txtUserName = mView.findViewById(R.id.txt_user_name);
    }
}
