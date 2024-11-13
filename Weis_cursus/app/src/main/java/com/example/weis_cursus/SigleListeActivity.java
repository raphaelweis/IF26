package com.example.weis_cursus;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class SigleListeActivity extends AppCompatActivity {
    ListView sigleListLv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sigle_liste);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        sigleListLv = findViewById(R.id.sigle_liste_lv);

        ProgrammeISI isi = new ProgrammeISI();
        ArrayList<String> sigles = isi.getSigles();
        SigleAdapter sigleAdapter = new SigleAdapter(this, R.layout.sigle_item, sigles);
        sigleListLv.setAdapter(sigleAdapter);
    }
}