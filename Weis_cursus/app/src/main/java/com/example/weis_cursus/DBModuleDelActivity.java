package com.example.weis_cursus;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class DBModuleDelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dbmodule_del);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        final Button deleteBtn = findViewById(R.id.bdoh_delete_btn);
        final TextInputEditText sigleIt = findViewById(R.id.bdoh_sigle_it);

        deleteBtn.setOnClickListener(v -> {
            final BDOpenHelper bdOpenHelper = new BDOpenHelper(this);

            bdOpenHelper.deleteModule(Objects.requireNonNull(sigleIt.getText()).toString());

            bdOpenHelper.close();
            finish();
        });
    }
}