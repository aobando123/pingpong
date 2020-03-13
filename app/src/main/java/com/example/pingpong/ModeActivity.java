package com.example.pingpong;

import android.content.Intent;
import android.os.Bundle;

import com.example.pingpong.activities.PongActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

public class ModeActivity extends AppCompatActivity {

    boolean isTwoPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle extras = getIntent().getExtras();
        setTheme(extras.getInt("themeId"));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode);
        this.isTwoPlayer =  extras.getBoolean("isTwoPlayer");
    }

    public  void setTime(View view) {
        Intent intent = new Intent(this, DifficultiesActivity.class);
        intent.putExtra("isTwoPlayer", this.isTwoPlayer);
        intent.putExtra("mode", "Time");
        startActivity(intent);
    }

    public  void setScore(View view) {
        Intent intent = new Intent(this, DifficultiesActivity.class);
        intent.putExtra("isTwoPlayer", this.isTwoPlayer);
        intent.putExtra("mode", "Score");
        startActivity(intent);
    }

}
