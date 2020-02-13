package com.practice.culture;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.practice.culture.login.LoginActivity;

public class ChoiceWay extends AppCompatActivity {
    SharedPreferences sp;
    Intent i=new Intent();
Button movie,ppt,start,btn_fire;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_way);
        movie=findViewById(R.id.movieOP);
        ppt=findViewById(R.id.pptOP);
        start=findViewById(R.id.btn_start);
        movie.setOnClickListener(listener);
        ppt.setOnClickListener(listener);
        start.setOnClickListener(listener);

        sp=getSharedPreferences("Player",MODE_PRIVATE);
        btn_fire=findViewById(R.id.btn_fire);
        btn_fire.setOnClickListener(listener);
        Toast.makeText(getApplicationContext(),"歡迎回來,"+sp.getString("PlayerName","陌生人"),Toast.LENGTH_SHORT).show();




    }

    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.movieOP:
                    i.setClass(getApplicationContext(), AnimOp.class);

                    break;
                case R.id.pptOP:
                    i.setClass(getApplicationContext(), Tzu_OP1.class);

                    break;
                case R.id.btn_start:
                    i.setClass(getApplicationContext(),MainActivity.class);
                    break;
                case R.id.btn_fire:
                    i.setClass(getApplicationContext(), LoginActivity.class);
            }
            startActivity(i);
            //finish();
        }
    };
}
