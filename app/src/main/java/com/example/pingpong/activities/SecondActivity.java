package com.example.pingpong.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import com.example.pingpong.MainActivity;
import com.example.pingpong.R;

public class SecondActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "Potato";
    public static int potato = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        switch(potato) {
            case R.id.btnThemeBlue:
                setTheme(R.style.BlueTheme);
                break;
            case R.id.btnThemeDark:
                setTheme(R.style.DarkTheme);
                break;
            default:
                setTheme(R.style.AppTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
    }

    public void buttonTapHandler(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        TextView editText = (TextView) findViewById(R.id.txtTitle);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        // startActivity(intent);
        startActivity(new Intent(getBaseContext(), PongActivity.class));

    }

    public void themeButtonHandler(View view) {
        potato = view.getId();

//        finish();
        overridePendingTransition(0, 0);
//        startActivity(getIntent());
//        startActivity(new Intent(getBaseContext(), SecondActivity.class));
        recreate();
        overridePendingTransition(0, 0);
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
