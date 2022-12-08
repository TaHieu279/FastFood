package com.tavanhieu.fastfood.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.tavanhieu.fastfood.R;
import com.tavanhieu.fastfood.my_class.BuyProduct;

import java.text.DecimalFormat;
import java.util.List;

public class ListProductOrderAdapter extends RecyclerView.Adapter<ListProductOrderAdapter.ViewHolderCart> {
    private List<BuyProduct> arr;

    @SuppressLint("NotifyDataSetChanged")
    public ListProductOrderAdapter(List<BuyProduct> arr) {
        this.arr = arr;
        notifyDataSetChanged();
    }

    public static class ViewHolderCart extends RecyclerView.ViewHolder {
        private ImageView imgItem;
        private TextView txtTitle, txtPrice, txtTotalPrice, txtNumberOfItem;

        public ViewHolderCart(@NonNull View itemView) {
            super(itemView);

            imgItem = itemView.findViewById(R.id.img_item_cart);
            txtTitle = itemView.findViewById(R.id.img_title_cart);
            txtNumberOfItem = itemView.findViewById(R.id.txt_sum_cart);
            txtPrice = itemView.findViewById(R.id.txt_price_cart);
            txtTotalPrice = itemView.findViewById(R.id.txt_total_price_cart);
        }
    }

    @NonNull
    @Override
    public ViewHolderCart onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_product_order, parent, false);
        return new ViewHolderCart(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCart holder, int position) {
        BuyProduct item = arr.get(position);
        DecimalFormat df = new DecimalFormat("0.00");
        if (item == null) return;
        //Load dữ liệu:
        holder.txtTitle.setText(String.valueOf(item.getTitle()));
        holder.txtNumberOfItem.setText(String.valueOf("x" + item.getNumber()));
        holder.txtPrice.setText(String.valueOf("$" + item.getPrice()));
        holder.txtTotalPrice.setText(String.valueOf("$" + df.format(item.getPrice() * item.getNumber())));
        //Tải ảnh:
        Picasso.get().load(item.getImage()).placeholder(R.drawable.load_product).centerInside().resize(200, 200).into(holder.imgItem);
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }
}
