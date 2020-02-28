package com.example.pingpong;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import com.example.pingpong.activities.PongActivity;
import com.example.pingpong.activities.SecondActivity;

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.DarkTheme);
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main_menu);
        startActivity(new Intent(getBaseContext(), SecondActivity.class));
    }

    public void buttonTapHandler(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        // startActivity(intent);
        startActivity(new Intent(getBaseContext(), PongActivity.class));

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
