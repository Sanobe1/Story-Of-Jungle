package com.storyofjungle.palm;

import android.content.Intent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void onClick(View v) {
        Button button = findViewById(v.getId());
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        switch (button.getTag().toString()) {

            case "start" : {
                startActivity(new Intent(this, GameActivity.class));
                finish();
                break;
            }

            case "policy": {
                startActivity(new Intent(this, PolicyActivity.class));
                finish();
                break;
            }
            case "exit": {
                finish();
                break;
            }

            default:
                break;
        }
    }
}