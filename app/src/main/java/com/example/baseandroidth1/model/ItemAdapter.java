package com.example.baseandroidth1.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baseandroidth1.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>{

    private List<Item> items;
    private ItemListener itemListener;

    public ItemAdapter(List<Item> items) {
        this.items = items;
    }

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
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
        holder.btnDelete.setOnClickListener(view -> {
            items.remove(position);
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public Item getItem (int position) {return items.get(position);}

    public void addItem (Item item) {
        if (item != null) items.add(item);
        notifyDataSetChanged();
    }

    public void setItem (Item item, int position) {
        if (item != null) items.set(position, item);
        notifyDataSetChanged();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private CircleImageView circleImageView;
        private TextView textView;
        private ImageButton btnDelete;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            initView(itemView);
            itemView.setOnClickListener(this);
        }

        private void initView (View view) {
            circleImageView = view.findViewById(R.id.img);
            textView = view.findViewById(R.id.textItem);
            btnDelete = view.findViewById(R.id.btnDelete);
        }

        @Override
        public void onClick(View view) {
            if (itemListener != null)
                itemListener.onClickItem(view, getAdapterPosition());
        }
    }

    public interface ItemListener {
        void onClickItem (View view, int position);
    }
}
