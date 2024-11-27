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

public class DBModuleAddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dbmodule_add);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        final Button addBtn = findViewById(R.id.bdoh_add_btn);
        final TextInputEditText sigleIt = findViewById(R.id.bdoh_sigle_it);
        final TextInputEditText parcoursIt = findViewById(R.id.bdoh_parcours_it);
        final TextInputEditText categoryIt = findViewById(R.id.bdoh_category_it);
        final TextInputEditText creditsIt = findViewById(R.id.bdoh_credit_it);


        addBtn.setOnClickListener(v -> {
            final BDOpenHelper bdOpenHelper = new BDOpenHelper(this);

            final Module module = new Module(
                    Objects.requireNonNull(sigleIt.getText()).toString(),
                    Objects.requireNonNull(parcoursIt.getText()).toString(),
                    Objects.requireNonNull(categoryIt.getText()).toString(),
                    Integer.parseInt(Objects.requireNonNull(creditsIt.getText()).toString())
            );
            bdOpenHelper.createModule(module);
            bdOpenHelper.close();
            finish();
        });

    }
}