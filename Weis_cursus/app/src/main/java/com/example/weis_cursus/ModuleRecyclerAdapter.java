package com.example.weis_cursus;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

public class ModuleRecyclerAdapter extends RecyclerView.Adapter<ModuleRecyclerAdapter.ViewHolder> {
    private final ArrayList<Module> localModules;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView sigle;
        private final TextView categorie;
        private final TextView parcours;
        private final TextView credits;

        public ViewHolder(View view) {
            super(view);
            sigle = view.findViewById(R.id.module_sigle_tv);
            categorie = view.findViewById(R.id.module_categorie_tv);
            parcours = view.findViewById(R.id.module_parcours_tv);
            credits = view.findViewById(R.id.module_credits_tv);
        }

        public TextView getSigle() {
            return sigle;
        }

        public TextView getCategorie() {
            return categorie;
        }

        public TextView getParcours() {
            return parcours;
        }

        public TextView getCredits() {
            return credits;
        }
    }

    public ModuleRecyclerAdapter(ArrayList<Module> modules) {
        localModules = modules;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.module_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        final Module module = localModules.get(position);

        viewHolder.getSigle().setText(module.getSigle());
        viewHolder.getCategorie().setText(module.getCategorie());
        viewHolder.getParcours().setText(module.getParcours());
        viewHolder.getCredits().setText(String.format(Locale.getDefault(), "%d", module.getCredit()));
    }

    @Override
    public int getItemCount() {
        return localModules.size();
    }
}
