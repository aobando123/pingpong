package com.example.pingpong;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;

import com.example.pingpong.activities.PongActivity;
import com.example.pingpong.views.PongView;

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startActivity(new Intent(getBaseContext(), PongActivity.class));

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
