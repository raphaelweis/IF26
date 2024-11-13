package com.example.weis_cursus;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextView mainTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mainTv = findViewById(R.id.main_tv);
        mainTv.setText(R.string.modules_du_programme_isi);
        mainTv.setTextSize(30);
        mainTv.setHeight(400);
        mainTv.setGravity(Gravity.CENTER_VERTICAL);
        mainTv.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
        mainTv.setTextColor(ContextCompat.getColor(this, R.color.blue));
        registerForContextMenu(mainTv);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Context Menu");
        getMenuInflater().inflate(R.menu.main_option_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        return onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.list_of_sigles) {
            listOfSigles();
            return true;
        }
        if (id == R.id.number_of_sigles) {
            numberOfSigles();
            return true;
        }
        if (id == R.id.complete_list) {
            completeList();
            return true;
        }
        if (id == R.id.list_of_cs) {
            listOfCS();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void listOfSigles() {
        Intent intent = new Intent(this, SigleListeActivity.class);
        startActivity(intent);
    }

    private void numberOfSigles() {
        ProgrammeISI isi = new ProgrammeISI();
        int numberOfSigles = isi.getSigles().size();
        Toast.makeText(this, String.format(Locale.getDefault(), "Il y a %d sigles.", numberOfSigles), Toast.LENGTH_LONG).show();
    }

    private void completeList() {
        Intent intent = new Intent(this, ModuleListeActivity.class);
        intent.putExtra("methode", "ALL");
        startActivity(intent);
    }

    private void listOfCS() {
        Intent intent = new Intent(this, ModuleListeActivity.class);
        intent.putExtra("methode", "CS");
        startActivity(intent);
    }
}