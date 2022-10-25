package com.tavanhieu.fastfood.fragment_main;

import android.annotation.SuppressLint;
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
import com.tavanhieu.fastfood.my_class.Popular;
import com.tavanhieu.fastfood.my_class.User;

import java.util.ArrayList;
import java.util.Objects;

public class Fragment_Home extends Fragment {
    private View mView;
    private RecyclerView rcvCategory, rcvPopular;
    private TextView txtUserName;
    private ArrayList<Categories> arr1;
    private ArrayList<Popular> arr2;

    @SuppressLint({"SetTextI18n", "ShowToast"})
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home, container, false);

        anhXa();
        getUserFromFirebase();
        getDataCategories();
        getDataPopular();

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
                            User user = snapshot.getValue(User.class);
                            //Gán dữ liệu ra text:
                            if (user != null) {
                                txtUserName.setText("Hi, " + user.getUserName());
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
        arr1.add(new Categories(R.drawable.cat_2, "Cake"));
        arr1.add(new Categories(R.drawable.cat_3, "Hot dog"));
        arr1.add(new Categories(R.drawable.cat_4, "Coca"));
        arr1.add(new Categories(R.drawable.cat_5, "Pie"));
        //Ánh xạ adapter category:
        RecyclerView.Adapter<CategoryAdapter.ViewHolderCategory> adapter = new CategoryAdapter(arr1);
        rcvCategory.setAdapter(adapter);
    }

    private void getDataPopular() {
        //Thêm dữ liệu cho mảng Popular:
        arr2 = new ArrayList<>();
        arr2.add(new Popular("Pepperoni pizza", "This is description for item popular in fast food app." +
                "This is description for item popular in fast food app 1." +
                "This is description for item popular in fast food app 2." +
                "This is description for item popular in fast food app 3." +
                "This is description for item popular in fast food app 4." +
                "This is description for item popular in fast food app 5." +
                "This is description for item popular in fast food app 6." +
                "This is description for item popular in fast food app 7.", R.drawable.pop_1, 9.76f));
        arr2.add(new Popular("Cheese Burger", "This is description for item popular in fast food app." +
                "This is description for item popular in fast food app 1." +
                "This is description for item popular in fast food app 2." +
                "This is description for item popular in fast food app 3.", R.drawable.pop_2, 8.5f));
        arr2.add(new Popular("Vegetable pizza", "This is description for item popular in fast food app.\n" +
                "This is description for item popular in fast food app 1.\n" +
                "This is description for item popular in fast food app 2.\n" +
                "This is description for item popular in fast food app 3.\n" +
                "This is description for item popular in fast food app 4.\n" +
                "This is description for item popular in fast food app 5.\n" +
                "This is description for item popular in fast food app 6.\n" +
                "This is description for item popular in fast food app 7.", R.drawable.pop_3, 10.8f));
        //Ánh xạ adapter popular:
        RecyclerView.Adapter<PopularAdapter.ViewHolderPopular> adapter = new PopularAdapter(arr2);
        rcvPopular.setAdapter(adapter);
    }

    private void anhXa() {
        rcvCategory = mView.findViewById(R.id.rcv_categories_main);
        rcvPopular = mView.findViewById(R.id.rcv_popular_main);
        txtUserName = mView.findViewById(R.id.txt_user_name);
    }
}
