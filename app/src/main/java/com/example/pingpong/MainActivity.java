package com.example.pingpong;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pingpong.activities.PongActivity;
import com.example.pingpong.activities.SecondActivity;

public class MainActivity extends Activity {


    public static final String EXTRA_MESSAGE = "Potato";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.DarkTheme);
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main_menu);
        startActivity(new Intent(getBaseContext(), SecondActivity.class));
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

    public void switchTapHandler(Switch view) {

        view.toggle();

        setTheme(R.style.DarkTheme);


        Log.d("Theme", getTheme().toString());

        finish();
        recreate();
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
