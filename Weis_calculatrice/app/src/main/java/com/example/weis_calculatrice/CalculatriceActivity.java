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

import java.util.ArrayList;

public class CalculatriceActivity extends AppCompatActivity {
    ArrayList<Ue> ues = new ArrayList<>();
    Button csButtonQty;
    Button tmButtonQty;
    Button stButtonQty;
    Button ttButtonQty;

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

        ues.add(new Ue(this, findViewById(R.id.c06_button), "TM", 3));
        ues.add(new Ue(this, findViewById(R.id.eg23_button), "TM", 6));
        ues.add(new Ue(this, findViewById(R.id.gl02_button), "CS", 6));
        ues.add(new Ue(this, findViewById(R.id.if10_button), "CS", 6));
        ues.add(new Ue(this, findViewById(R.id.if17_button), "CS", 6));
        ues.add(new Ue(this, findViewById(R.id.if22_button), "CS", 6));
        ues.add(new Ue(this, findViewById(R.id.if26_button), "TM", 6));
        ues.add(new Ue(this, findViewById(R.id.if29_button), "CS", 6));
        ues.add(new Ue(this, findViewById(R.id.if31_button), "TM", 6));
        ues.add(new Ue(this, findViewById(R.id.if34_button), "TM", 6));
        ues.add(new Ue(this, findViewById(R.id.lo10_button), "TM", 6));
        ues.add(new Ue(this, findViewById(R.id.lo12_button), "CS", 6));
        ues.add(new Ue(this, findViewById(R.id.nf21_button), "TM", 6));
        ues.add(new Ue(this, findViewById(R.id.st09_button), "ST", 30));
        ues.add(new Ue(this, findViewById(R.id.st10_button), "ST", 30));

        csButtonQty = findViewById(R.id.cs_button_qty);
        tmButtonQty = findViewById(R.id.tm_button_qty);
        stButtonQty = findViewById(R.id.st_button_qty);
        ttButtonQty = findViewById(R.id.tt_button_qty);
        csButtonQty.setText(Integer.toString(0));
        tmButtonQty.setText(Integer.toString(0));
        stButtonQty.setText(Integer.toString(0));
        ttButtonQty.setText(Integer.toString(0));

        for (Ue ue : ues) {
            ue.button.setOnClickListener(view -> {
                if (view.isActivated()) {
                    switch (ue.category) {
                        case "CS":
                            removeCredits(csButtonQty, ue.credits);
                            break;
                        case "TM":
                            removeCredits(tmButtonQty, ue.credits);
                            break;
                        case "ST":
                            removeCredits(stButtonQty, ue.credits);
                            break;
                        default:
                            break;
                    }
                    removeCredits(ttButtonQty, ue.credits);
                    view.setActivated(false);
                    ue.button.setBackgroundColor(getResources().getColor(R.color.LightGrey, getTheme()));
                } else {
                    switch (ue.category) {
                        case "CS":
                            addCredits(csButtonQty, ue.credits);
                            break;
                        case "TM":
                            addCredits(tmButtonQty, ue.credits);
                            break;
                        case "ST":
                            addCredits(stButtonQty, ue.credits);
                            break;
                        default:
                            break;
                    }
                    addCredits(ttButtonQty, ue.credits);
                    view.setActivated(true);
                    ue.button.setBackgroundColor(ue.backgroundColor);
                }
            });
        }
    }

    public void onRetClick(View view) {
        Intent intent = new Intent();
        intent.putExtra("com.example.weis_calculatrice.result", String.format("Crédits CS = %s\nCrédits TM = %s\nCrédits ST = %s\nCrédits TOT = %s", csButtonQty.getText(), tmButtonQty.getText(), stButtonQty.getText(), ttButtonQty.getText()));
        setResult(RESULT_OK, intent);
        finish();
    }

    @SuppressLint("SetTextI18n")
    private void addCredits(Button button, int credits) {
        button.setText(Integer.toString(Integer.parseInt(button.getText().toString()) + credits));
    }

    @SuppressLint("SetTextI18n")
    private void removeCredits(Button button, int credits) {
        button.setText(Integer.toString(Integer.parseInt(button.getText().toString()) - credits));
    }
}