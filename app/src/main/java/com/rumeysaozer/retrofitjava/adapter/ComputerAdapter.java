package com.rumeysaozer.retrofitjava.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.rumeysaozer.retrofitjava.databinding.ItemRowBinding;
import com.rumeysaozer.retrofitjava.model.Computer;
import java.util.ArrayList;

public class ComputerAdapter extends RecyclerView.Adapter<ComputerAdapter.ComputerHolder> {
    private ArrayList<Computer> computerList;
    public ComputerAdapter(ArrayList<Computer> computerList){
        this.computerList = computerList;
    }


    @NonNull
    @Override
    public ComputerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      @NonNull ItemRowBinding binding = ItemRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ComputerHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull ComputerHolder holder, int position) {
    holder.itemRowBinding.os.setText(computerList.get(position).os);
    holder.itemRowBinding.platform.setText(computerList.get(position).platform);
    holder.itemRowBinding.stack.setText(computerList.get(position).stack);
    holder.itemRowBinding.type.setText(computerList.get(position).type);
    }

    @Override
    public int getItemCount() {
        return computerList.size();
    }

    public class ComputerHolder extends RecyclerView.ViewHolder {
        ItemRowBinding itemRowBinding;
        public ComputerHolder(@NonNull ItemRowBinding binding) {
            super(binding.getRoot());
            this.itemRowBinding = binding;
        }
    }
}
