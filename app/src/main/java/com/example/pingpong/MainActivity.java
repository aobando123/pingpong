package com.example.pingpong;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pingpong.activities.PongActivity;
import com.example.pingpong.activities.SecondActivity;

public class MainActivity extends Activity {

    public static int themeId = R.style.AppTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(themeId);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
//        startActivity(new Intent(getBaseContext(), SecondActivity.class));
    }

    public void vsAI(View view) {
        Intent intent = new Intent(this, DifficultiesActivity.class);
        intent.putExtra("isTwoPlayer", false);
        intent.putExtra("themeId", themeId);
        startActivity(intent);

    }
    public void vsPlayer(View view) {
        Intent intent = new Intent(this, DifficultiesActivity.class);
        intent.putExtra("isTwoPlayer", true);
        intent.putExtra("themeId", themeId);
        startActivity(intent);

    }

    public void themeButtonHandler(View view) {
        switch(view.getId()) {
            case R.id.btnThemeBlue:
                themeId = R.style.BlueTheme;
                break;
            case R.id.btnThemeDark:
                themeId = R.style.DarkTheme;
                break;
            default:
                themeId = R.style.AppTheme;
        }
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
