package com.example.hello;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private RadioGroup radioGroup;
    private Button save;
    private RadioButton radioLightMode, radioDarkMode;
    private ConstraintLayout mainLayout;
   ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = findViewById(R.id.radioGroup);
        radioLightMode = findViewById(R.id.sang);
        radioDarkMode = findViewById(R.id.toi);
        mainLayout = findViewById(R.id.constraintLayout);

        sharedPreferences = getSharedPreferences("AppPreferences", MODE_PRIVATE);

        applyTheme(sharedPreferences.getBoolean("DARK_MODE", false));


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.sang) {
                    applyTheme(false);
                    saveModePreference(false);
                } else if (checkedId == R.id.toi) {
                    applyTheme(true);
                    saveModePreference(true);
                }
            }
        });
    }

    private void applyTheme(boolean isDarkMode) {
        if (isDarkMode) {
            mainLayout.setBackgroundColor(ContextCompat.getColor(this, android.R.color.black));
        } else {
            mainLayout.setBackgroundColor(ContextCompat.getColor(this, android.R.color.white));
        }
    }

    private void saveModePreference(boolean isDarkMode) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("DARK_MODE", isDarkMode);
        editor.apply();
    }
}
