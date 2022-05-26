package com.example.newcalc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

import java.util.Objects;

public class SettingsActivity extends AppCompatActivity implements Constants {

    SwitchCompat switchTheme;//свич отвечающий за тему приложения

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Objects.requireNonNull(getSupportActionBar()).hide();//скрытие экшнбара

        switchTheme = findViewById(R.id.switchTheme);
        switchTheme.setText("Night Theme");
        switchTheme.setChecked(getIntent().getExtras().getInt(THEME) ==
                AppCompatDelegate.MODE_NIGHT_YES);//если тема ночная - свич-вкл

        switchTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentResult = new Intent();

                if (switchTheme.isChecked())
                    intentResult.putExtra(THEME, AppCompatDelegate.MODE_NIGHT_YES);
                else
                    intentResult.putExtra(THEME, AppCompatDelegate.MODE_NIGHT_NO);

                setResult(RESULT_OK, intentResult);
                finish();
            }
        });

    }
}