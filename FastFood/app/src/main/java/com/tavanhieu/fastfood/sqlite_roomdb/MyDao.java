package com.tavanhieu.fastfood.sqlite_roomdb;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.tavanhieu.fastfood.my_class.BuyProduct;
import java.util.List;

@androidx.room.Dao
public interface MyDao {
    //Thêm vào một sản phẩm:
    @Insert
    void insert(BuyProduct buyProduct);

    //Cập nhật sản phẩm:
    @Update
    void update(BuyProduct buyProduct);

    //Xóa sản phẩm:
    @Query("Delete from MyBuyProduct")
    void delete();

    @Query("Select * from MyBuyProduct") //Lấy ra tất cả danh sách
    List<BuyProduct> getList();

    @Query("Select * from MyBuyProduct") //Lấy ra tất cả danh sách cho liveData
    LiveData<List<BuyProduct>> getListForLiveData();

    @Query("Delete from MyBuyProduct where id = :mId") //Xóa sản phẩm có id trùng
    void delete(int mId);

    @Query("Select * from MyBuyProduct where id = :mId") //Lấy ra sản phẩm có id trùng
    BuyProduct getByTitle(int mId);

    @Query("Update MyBuyProduct set number = :mNumber where title = :mTitle") //Cập nhật số lượng sản phẩm mua theo tên
    void updateByTitle(int mNumber, String mTitle);
}
