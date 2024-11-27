package com.example.weis_cursus;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Locale;

public class ModuleListeAdapter extends ArrayAdapter<Module> {
    final ArrayList<Module> liste;
    final Context contexte;
    final int ressource;

    public ModuleListeAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Module> objects) {
        super(context, resource, objects);
        this.liste = objects;
        this.contexte = context;
        this.ressource = resource;
    }

    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = ((Activity) contexte).getLayoutInflater();
        @SuppressLint("ViewHolder") View view = inflater.inflate(ressource, parent, false);

        Module module = liste.get(position);
        TextView sigle = view.findViewById(R.id.module_sigle_tv);
        TextView categorie = view.findViewById(R.id.module_categorie_tv);
        TextView parcours = view.findViewById(R.id.module_parcours_tv);
        TextView credits = view.findViewById(R.id.module_credits_tv);
        sigle.setText(module.getSigle());
        categorie.setText(module.getCategorie());
        parcours.setText(module.getParcours());
        credits.setText(String.format(Locale.getDefault(), "%d", module.getCredit()));

        return view;
    }
}
