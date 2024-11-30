package com.example.weis_calculatrice;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.function.BiConsumer;

public class ModuleViewHolder extends RecyclerView.ViewHolder {
    private Module module;
    private final Button moduleItemView;

    private ModuleViewHolder(View itemView, ArrayList<Button> qtyButtons) {
        super(itemView);
        moduleItemView = itemView.findViewById(R.id.moduleButton);

        moduleItemView.setOnClickListener(v -> {
            System.out.println(module.getSigle());
            BiConsumer<Button, Integer> updateCredits = v.isActivated() ?
                    this::removeCredits :
                    this::addCredits;
            switch (module.getCategorie()) {
                case "CS":
                    updateCredits.accept(qtyButtons.get(0), module.getCredit());
                    break;
                case "TM":
                    updateCredits.accept(qtyButtons.get(1), module.getCredit());
                    break;
                case "ST":
                    updateCredits.accept(qtyButtons.get(2), module.getCredit());
                    break;
            }
            updateCredits.accept(qtyButtons.get(3), module.getCredit());
        });
    }

    static ModuleViewHolder create(ViewGroup parentView, ArrayList<Button> qtyButtons) {
        View itemView = LayoutInflater.from(parentView.getContext())
                .inflate(R.layout.recyclerview_item, parentView, false);
        return new ModuleViewHolder(itemView, qtyButtons);
    }

    public void bind(Module module) {
        this.module = module;
        moduleItemView.setText(module.getSigle());
    }

    @SuppressLint("SetTextI18n")
    private void addCredits(Button button, int credits) {
        button.setText(Integer.toString(Integer.parseInt(button.getText().toString()) + credits));
    }

    @SuppressLint("SetTextI18n")
    private void removeCredits(Button button, int credits) {
        button.setText(Integer.toString(Integer.parseInt(button.getText().toString()) - credits));
    }
}