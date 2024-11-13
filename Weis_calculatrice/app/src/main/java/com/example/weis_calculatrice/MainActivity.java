package com.example.weis_calculatrice;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText nameInput, surnameInput, resultInput;
    Button b1, b2;

    ActivityResultLauncher<Intent> activityLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), o -> {
        assert o.getData() != null;
        String result = o.getData().getStringExtra("com.example.weis_calculatrice.result");
        resultInput.setText(result);
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nameInput = findViewById(R.id.name_input);
        surnameInput = findViewById(R.id.surname_input);
        resultInput = findViewById(R.id.result_input);
        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);

        b2.setOnClickListener(this);
        b1.setOnClickListener(this);

        b1.setOnLongClickListener(view -> {
            Intent intent = new Intent(view.getContext(), InformationActivity.class);
            intent.putExtra("com.example.weis_calculatrice.name", nameInput.getText().toString());
            intent.putExtra("com.example.weis_calculatrice.surname", surnameInput.getText().toString());
            startActivity(intent);
            return true;
        });
        b2.setOnLongClickListener(view -> {
            Intent intent = new Intent(view.getContext(), CalculatriceActivity.class);
            activityLauncher.launch(intent);
            return true;
        });
    }

    public void onClick(View view) {
        if (view.isActivated()) {
            view.setActivated(false);
            view.setBackgroundColor(Color.GREEN);
        } else {
            view.setActivated(true);
            view.setBackgroundColor(Color.RED);
        }
    }
}