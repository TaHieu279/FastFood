package com.tavanhieu.fastfood.view_model;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.tavanhieu.fastfood.my_class.BuyProduct;
import com.tavanhieu.fastfood.sqlite_roomdb.MyDatabase;
import java.util.List;

@SuppressLint("StaticFieldLeak")
public class MyViewModel extends ViewModel {
    private LiveData<List<BuyProduct>> arrLiveData;

    public LiveData<List<BuyProduct>> getListForLiveData(Context context) {
        if(arrLiveData == null)
            arrLiveData = MyDatabase.getMyInstances(context).myDao().getListForLiveData();
        return arrLiveData;
    }

    public void insert(Context context, BuyProduct buyProduct) {
        MyDatabase.getMyInstances(context).myDao().insert(buyProduct);
    }
}
