package com.example.weis_cursus;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RoomModuleListActivity extends AppCompatActivity {

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

        RecyclerView moduleListeRv = findViewById(R.id.module_recycler_rv);
        final ModuleEntityRecyclerAdapter adapter = new ModuleEntityRecyclerAdapter(
                new ModuleEntityRecyclerAdapter.ModuleEntityDiff()
        );
        moduleListeRv.setAdapter(adapter);
        moduleListeRv.setLayoutManager(new LinearLayoutManager(this));

        // Get a new or existing ViewModel from ViewModelProvider.
        ModuleViewModel moduleViewModel = new ViewModelProvider(this).get(ModuleViewModel.class);

        // Add an observer on the LiveData returned by getAllModules.
        // The onChanged() method fires when the observed data changes and the activity is in the
        // foreground.
        moduleViewModel.getAllModules().observe(this, adapter::submitList);
    }
}