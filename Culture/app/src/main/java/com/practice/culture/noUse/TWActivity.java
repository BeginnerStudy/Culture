package com.practice.culture.noUse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.practice.culture.BattleActivity;
import com.practice.culture.R;

public class TWActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tw);
        Button Back=(Button)findViewById(R.id.returnGame);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), BattleActivity.class);
                startActivity(i);
                finish();

            }
        });
    }
}
