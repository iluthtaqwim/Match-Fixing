package com.example.iluth.finalprojectpam;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView btmview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btmview = findViewById(R.id.btmnav);
        btmview.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.btm_match:
                Toast.makeText(this, "Ini match", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btm_team:
                Toast.makeText(this, "Ini Team", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btm_fav:
                Toast.makeText(this, "Ini Favorite", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;

    }
}
