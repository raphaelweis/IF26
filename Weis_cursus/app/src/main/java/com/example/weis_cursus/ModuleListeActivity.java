package com.example.weis_cursus;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ModuleListeActivity extends AppCompatActivity {
    ListView moduleListeLv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_module_liste);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        moduleListeLv = findViewById(R.id.module_liste_lv);

        String methode = getIntent().getStringExtra("methode");

        ProgrammeISI isi = new ProgrammeISI();
        ArrayList<Module> modules = isi.getModules(methode != null ? methode : "ALL");
        ModuleListeAdapter moduleListeAdapter = new ModuleListeAdapter(this, R.layout.module_item, modules);
        moduleListeLv.setAdapter(moduleListeAdapter);
    }
}