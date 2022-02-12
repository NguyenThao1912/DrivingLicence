package com.example.drivinglicense.adapter.tip;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drivinglicense.R;
import com.example.drivinglicense.databinding.ItemTipBinding;
import com.example.drivinglicense.model.Tip;
import com.example.drivinglicense.model.TipCategory;

import java.util.ArrayList;
import java.util.List;

public class TipAdapter extends RecyclerView.Adapter<TipAdapter.TipHolder> {
    private List<TipCategory> tipCategories = new ArrayList<>();
    private List<Tip> tips = new ArrayList<>();

    public TipAdapter(List<TipCategory> tipCategories, List<Tip> tips) {
        this.tipCategories = tipCategories;
        this.tips = tips;
    }

    @NonNull
    @Override
    public TipHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TipHolder(ItemTipBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull TipHolder holder, int position) {
        holder.binding.btName.setText(tipCategories.get(position).getName());
        String contentTip = "";
        for (Tip model:
             tips) {
            if(model.getCategory()==tipCategories.get(position).getCategory()){
                contentTip += model.getContent()+"\n\n";
            }
        }
        holder.binding.tvContent.setText(contentTip);
        holder.binding.tvContent.setVisibility(View.GONE);

        //TODO set mà cho view phía sau
        switch (position){
            case 0:
                holder.binding.viewBehind.setCardBackgroundColor(holder.itemView.getContext().getColor(R.color.red_500));
                break;
            case 1:
                holder.binding.viewBehind.setCardBackgroundColor(holder.itemView.getContext().getColor(R.color.green_500));
                break;
            case 2:
                holder.binding.viewBehind.setCardBackgroundColor(holder.itemView.getContext().getColor(R.color.blue_500));
                break;
            case 3:
                holder.binding.viewBehind.setCardBackgroundColor(holder.itemView.getContext().getColor(R.color.orange_500));
                break;
            case 4:
                holder.binding.viewBehind.setCardBackgroundColor(holder.itemView.getContext().getColor(R.color.purple_500));
                break;
        }
        holder.binding.btName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.binding.tvContent.isShown()){
                    holder.binding.tvContent.setVisibility(View.GONE);
                    holder.binding.ivArrowDown.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_36);
                }
                else {
                    holder.binding.tvContent.setVisibility(View.VISIBLE);
                    holder.binding.ivArrowDown.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_36);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return tipCategories.size();
    }

    class TipHolder extends RecyclerView.ViewHolder {
        private ItemTipBinding binding;
        public TipHolder(ItemTipBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
