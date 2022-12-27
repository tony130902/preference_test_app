package com.example.android.preference;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.CheckBoxPreference;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.Preference;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements
        SharedPreferences.OnSharedPreferenceChangeListener{

    TextView color_textview , age_textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        color_textview = findViewById(R.id.color_textview);
        age_textview = findViewById(R.id.age_textview);

        Load_Settings();
    }

    public void load_Text(SharedPreferences sharedPreferences){
        color_textview.setText(sharedPreferences.getString(getString(R.string.choice_list_key),
                getString(R.string.choice_list_default)));
    }

    public void load_age_text(SharedPreferences sharedPreferences){
        age_textview.setText(sharedPreferences.getString(getString(R.string.age_key),
                getString(R.string.age_default)));
    }

    public void Load_Settings(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean check_box = sharedPreferences.getBoolean(getString(R.string.checkbox_key),
                getResources().getBoolean(R.bool.checkbox_default));

        if (check_box){
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linear_layout);
            linearLayout.setBackgroundColor(Color.parseColor("#222222"));
        }else {
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linear_layout);
            linearLayout.setBackgroundColor(Color.parseColor("#ffffff"));
        }

        load_Text(sharedPreferences);

        load_age_text(sharedPreferences);

        sharedPreferences.registerOnSharedPreferenceChangeListener(this);

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        if(key.equals(getString(R.string.checkbox_key))){
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linear_layout);
            linearLayout.setBackgroundColor(Color.parseColor("#222222"));
        }
        else {
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linear_layout);
            linearLayout.setBackgroundColor(Color.parseColor("#ffffff"));
        }

        if(key.equals(getString(R.string.choice_list_key))){
            load_Text(sharedPreferences);
        }else if(key.equals(getString(R.string.age_key))){
            load_age_text(sharedPreferences);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this).
                unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu , menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.settings){
            Intent intent = new Intent(this , SettingsActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


}