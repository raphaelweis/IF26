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

public class SigleAdapter extends ArrayAdapter<String> {
    final ArrayList<String> liste;
    final Context contexte;
    final int ressource;

    public SigleAdapter(@NonNull Context context, int resource, @NonNull ArrayList<String> objects) {
        super(context, resource, objects);
        this.liste = objects;
        this.contexte = context;
        this.ressource = resource;
    }

    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = ((Activity) contexte).getLayoutInflater();
        @SuppressLint("ViewHolder") View view = inflater.inflate(ressource, parent, false);

        String module = liste.get(position);
        TextView tv = view.findViewById(R.id.sigle_tv);
        tv.setText(module);
        return view;
    }
}
