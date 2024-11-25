package com.example.weis_cursus;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ModuleRecyclerActivity extends AppCompatActivity {
    RecyclerView moduleListeRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_module_recycler);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        moduleListeRv = findViewById(R.id.module_recycler_rv);

        ProgrammeISI isi = new ProgrammeISI();
        ArrayList<Module> modules = isi.getModules("ALL");
        ModuleRecyclerAdapter moduleRecyclerAdapter = new ModuleRecyclerAdapter(modules);
        moduleListeRv.setLayoutManager(new LinearLayoutManager(this));
        moduleListeRv.setAdapter(moduleRecyclerAdapter);
    }
}