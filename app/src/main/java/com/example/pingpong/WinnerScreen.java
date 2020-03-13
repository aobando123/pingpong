package com.example.pingpong;

import android.content.Intent;
import android.os.Bundle;

import com.example.pingpong.activities.PongActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

public class WinnerScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle extras = getIntent().getExtras();
        setTheme(extras.getInt("themeId"));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner_screen);
        TextView winner = findViewById(R.id.txtResults);
        String a = extras.getString("winner", "results");
        String b = extras.getString("winner");
        winner.setText(a);
    }

    public void onPlayAgain(View view) {
        Bundle extras = getIntent().getExtras();
        Intent intent = new Intent(this, PongActivity.class);
        intent.putExtra("ballSpeed", extras.getInt("ballSpeed"));
        intent.putExtra("computerProbability", extras.getFloat("computerProbability"));
        intent.putExtra("isTwoPlayer", extras.getBoolean("isTwoPlayer"));
        intent.putExtra("mode", extras.getString("mode"));
        startActivity(intent);
    }

    public  void onMainMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
