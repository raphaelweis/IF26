package com.example.weis_calculatrice;

import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import java.util.ArrayList;

public class ModuleListAdapter extends ListAdapter<Module, ModuleViewHolder> {
    private final ArrayList<Button> qtyButtons;

    public ModuleListAdapter(
            @NonNull DiffUtil.ItemCallback<Module> diffCallback,
            ArrayList<Button> qtyButtons) {
        super(diffCallback);
        this.qtyButtons = qtyButtons;
    }

    @NonNull
    @Override
    public ModuleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ModuleViewHolder.create(parent, qtyButtons);
    }

    @Override
    public void onBindViewHolder(ModuleViewHolder holder, int position) {
        Module current = getItem(position);
        holder.bind(current);
    }

    static class ModuleDiff extends DiffUtil.ItemCallback<Module> {

        @Override
        public boolean areItemsTheSame(@NonNull Module oldItem, @NonNull Module newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Module oldItem, @NonNull Module newItem) {
            return oldItem.getSigle().equals(newItem.getSigle());
        }
    }
}