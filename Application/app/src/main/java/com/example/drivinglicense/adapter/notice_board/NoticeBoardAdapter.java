package com.example.drivinglicense.adapter.notice_board;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drivinglicense.databinding.ItemNoticeBoardBinding;
import com.example.drivinglicense.model.NoticeBoard;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class NoticeBoardAdapter extends RecyclerView.Adapter<NoticeBoardAdapter.NoticeBoardHolder> {
    private List<NoticeBoard> list = new ArrayList<>();


    public NoticeBoardAdapter(List<NoticeBoard> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public NoticeBoardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoticeBoardHolder(
                ItemNoticeBoardBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeBoardHolder holder, int position) {
        holder.boardBinding.setData(list.get(position));
        try {
            InputStream is = holder.itemView.getContext().getAssets().open("signal/"+list.get(position).getIcon());
            Bitmap  bitmap = BitmapFactory.decodeStream(is);
            holder.boardBinding.ivNb.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class NoticeBoardHolder extends RecyclerView.ViewHolder {
        private ItemNoticeBoardBinding boardBinding;
        public NoticeBoardHolder(ItemNoticeBoardBinding binding) {
            super(binding.getRoot());
            this.boardBinding = binding;
        }

    }
}
