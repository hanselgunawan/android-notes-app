package com.hanseltritama.notesapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private ArrayList<String> mList;

    public MainAdapter(ArrayList<String> mList) {
        this.mList = mList; // get an ArrayList from MainActivity
    }

    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.ViewHolder holder, int position) {
        holder.textView.setText("hahahaha");
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class  ViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.row_item);

        }
    }
}
