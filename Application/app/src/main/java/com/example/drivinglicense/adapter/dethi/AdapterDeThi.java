package com.example.drivinglicense.adapter.dethi;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drivinglicense.databinding.ItemDethiBinding;
import com.example.drivinglicense.global.AppGlobal;
import com.example.drivinglicense.model.Licence;
import com.example.drivinglicense.views.QuestionActivity;
import com.example.drivinglicense.views.TestKit;

import java.util.ArrayList;
import java.util.List;

public class AdapterDeThi extends RecyclerView.Adapter<AdapterDeThi.DeThiViewHolder> {

    private List<Licence> list = new ArrayList<>();
    private onItemListener onItemListener;
    public AdapterDeThi(List<Licence> list,onItemListener onItemListener) {
        this.list = list;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public DeThiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DeThiViewHolder(ItemDethiBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false),this.onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull DeThiViewHolder holder, int position) {
        if(position >=0 && position <10)
            holder.binding.testNumber.setText("0"+(position+1));
        else
            holder.binding.testNumber.setText(""+(position+1));
        holder.binding.title.setText("Đề Số "+(position+1));
        holder.binding.comment.setText("25 câu/19 phút");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class DeThiViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
        private ItemDethiBinding binding;
        private onItemListener onItemListener;
        public DeThiViewHolder(@NonNull ItemDethiBinding binding, onItemListener onItemListener) {
            super(binding.getRoot());
            this.binding = binding;
            itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            this.binding.txtResult.setOnClickListener(this);
            this.onItemListener = onItemListener;
        }
        @Override
        public void onClick(View v) {
            this.onItemListener.onClick_LamBai(getAdapterPosition());
        }
    }
    public interface onItemListener{
        void onClick_LamBai(int position);
    }
}
