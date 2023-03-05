package com.example.baseandroidth1.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baseandroidth1.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>{

    private List<Item> items;

    public ItemAdapter(List<Item> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item item = items.get(position);
        if (item == null) return;
        holder.circleImageView.setImageResource(item.getImg());
        holder.textView.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView circleImageView;
        private TextView textView;
        private Button btnEdit;
        private Button btnDelete;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView (View view) {
            circleImageView = view.findViewById(R.id.img);
            textView = view.findViewById(R.id.textItem);
            btnEdit = view.findViewById(R.id.btnEdit);
            btnDelete = view.findViewById(R.id.btnDelete);
        }
    }
}
