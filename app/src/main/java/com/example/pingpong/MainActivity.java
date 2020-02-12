package com.example.pingpong;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.EditText;

import com.example.pingpong.activities.PongActivity;
import com.example.pingpong.views.PongView;

public class MainActivity extends Activity {


    public static final String EXTRA_MESSAGE = "Potato";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
    }

    public void buttonTapHandler(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        EditText editText = (EditText) findViewById(R.id.txtTitle);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        // startActivity(intent);
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
