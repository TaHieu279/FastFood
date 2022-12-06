package com.tavanhieu.fastfood.fragment_main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tavanhieu.fastfood.R;
import com.tavanhieu.fastfood.my_class.Payment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

public class Fragment_Statistic extends Fragment {
    private View mView;
    private TextView txtDate;
    private ImageView imgSearch;
    private BarChart barChart;

    private ArrayList<BarEntry> barEntries = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_statistics, container, false);

        //ánh xạ:
        anhXa();
        //Load dữ liệu cho tuần hiện tại:
        loadData();

        return mView;
    }

    private void loadData() {
        //Lấy ra ngày hiện tại:
        Calendar c = Calendar.getInstance();
        //Chuyển về ngày đầu tuần:
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            c.add(Calendar.DAY_OF_MONTH, -6);
        } else {
            int day = c.get(Calendar.DAY_OF_WEEK) - Calendar.MONDAY;
            c.add(Calendar.DAY_OF_MONTH, -day);
        }
        //Lập bộ giá trị cho biểu đồ:
        for (int i = 1; i <= 7; i++) {
            @SuppressLint("SimpleDateFormat")
            String date = new SimpleDateFormat("dd/MM/yyyy").format(c.getTime());
            float price = 0f;
            getPriceByDayOfWeek("3122022", price);
            barEntries.add(new BarEntry(i, price));
        }
        //Tạo bộ dữ liệu
        BarDataSet barDataSet = new BarDataSet(barEntries, "Total payment");
        barDataSet.setDrawValues(false);
        //Set bộ dữ liệu cho biểu đồ
        barChart.setData(new BarData(barDataSet));
        barChart.animateY(3000);
        barChart.getDescription().setText("Payment");
    }

    private void getPriceByDayOfWeek(String date, float price) {
        //Không add được vào mảng
        ArrayList<Payment> arr = new ArrayList<>();
        //Lấy ra tổng tiền thanh toán trong ngày:
        FirebaseDatabase
                .getInstance()
                .getReference()
                .child("TablePayment")
                .child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
                .child(date)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            Payment payment = dataSnapshot.getValue(Payment.class);
                            if (payment != null) {
                                arr.add(payment);
//                                price += payment.getTotalPayment();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
    }

    private void anhXa() {
        txtDate = mView.findViewById(R.id.txt_date);
        barChart = mView.findViewById(R.id.bar_chart);
        imgSearch = mView.findViewById(R.id.img_search);
    }
}
