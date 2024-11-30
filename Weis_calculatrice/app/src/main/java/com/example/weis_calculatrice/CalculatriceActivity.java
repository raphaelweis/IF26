package com.example.weis_calculatrice;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CalculatriceActivity extends AppCompatActivity {
    final ArrayList<Button> qtyButtons = new ArrayList<>();
    Button retButton;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculatrice);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        qtyButtons.add(findViewById(R.id.cs_button_qty));
        qtyButtons.add(findViewById(R.id.tm_button_qty));
        qtyButtons.add(findViewById(R.id.st_button_qty));
        qtyButtons.add(findViewById(R.id.tt_button_qty));
        qtyButtons.get(0).setText(Integer.toString(0));
        qtyButtons.get(1).setText(Integer.toString(0));
        qtyButtons.get(2).setText(Integer.toString(0));
        qtyButtons.get(3).setText(Integer.toString(0));

        retButton = findViewById(R.id.ret_button);

        retButton.setOnClickListener(this::onRetClick);

        RecyclerView recyclerView = findViewById(R.id.module_buttons_rv);
        final ModuleListAdapter adapter = new ModuleListAdapter(new ModuleListAdapter.ModuleDiff(), qtyButtons);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));

        ModuleViewModel moduleViewModel = new ViewModelProvider(this).get(ModuleViewModel.class);
        moduleViewModel.getAllModules().observe(this, adapter::submitList);
    }

    private void onRetClick(View ignoredView) {
        Intent intent = new Intent();
        intent.putExtra("com.example.weis_calculatrice.result", String.format("Crédits CS = %s\nCrédits TM = %s\nCrédits ST = %s\nCrédits TOT = %s",
                qtyButtons.get(0).getText(),
                qtyButtons.get(1).getText(),
                qtyButtons.get(2).getText(),
                qtyButtons.get(3).getText()));
        setResult(RESULT_OK, intent);
        finish();
    }
}