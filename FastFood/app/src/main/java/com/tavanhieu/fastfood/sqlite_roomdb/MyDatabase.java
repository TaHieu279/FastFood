package com.tavanhieu.fastfood.sqlite_roomdb;

import android.content.Context;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.tavanhieu.fastfood.my_class.BuyProduct;
import com.tavanhieu.fastfood.my_class.Popular;

@androidx.room.Database(entities = BuyProduct.class, version = 1)
public abstract class MyDatabase extends RoomDatabase {
    public static MyDatabase instance;
    public abstract MyDao myDao();
    //Khởi tạo biến thể hiện cho Room Database khi được khởi tạo
    public static MyDatabase getMyInstances(Context context) {
        if(instance == null)
            instance = Room.databaseBuilder(context, MyDatabase.class, "My_Database").allowMainThreadQueries().build();
        return instance;
    }
}
