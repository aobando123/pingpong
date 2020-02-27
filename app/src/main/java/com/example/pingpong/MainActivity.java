package com.example.pingpong;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pingpong.activities.PongActivity;
import com.example.pingpong.views.PongView;

public class MainActivity extends Activity {


    public static final String EXTRA_MESSAGE = "Potato";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
    }

    public void vsAI(View view) {
        Intent intent = new Intent(this, DifficultiesActivity.class);
        intent.putExtra("isTwoPlayer", false);
        startActivity(intent);

    }
    public void vsPlayer(View view) {
        Intent intent = new Intent(this, DifficultiesActivity.class);
        intent.putExtra("isTwoPlayer", true);
        startActivity(intent);

    }

    // This method executes when the player starts the game
    @Override
    protected void onResume() {
        super.onResume();
    }

    // This method executes when the player quits the game
    @Override
    protected void onPause() {
        super.onPause();
    }
}
