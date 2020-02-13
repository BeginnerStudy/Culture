package com.practice.culture;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.practice.culture.UI.BitmapTool;

public class MainActivity extends AppCompatActivity {

    TextView t1,t2,name1,name2;
    ImageView img1,img2,scense;
    LinearLayout layout,layout1,layout2;
    Resources res;
    int progress;
    String script[];

    TextView TV_name=null;
    TextView TV_dialog = null;
    ImageView IV_icon = null;
    LinearLayout ActiveLayout = null;
    BitmapTool BT;
    Bitmap role1,role2,other,role_now;
    AnimationDrawable Anim,Anim2,testAnim;
    Bitmap bg1,bg2,bg3;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();


    }

    private void init(){
        t1=(TextView)findViewById(R.id.t1);
        t2=(TextView)findViewById(R.id.t2);
        name1=(TextView)findViewById(R.id.name1);
        name2=(TextView)findViewById(R.id.name2);
        img1=(ImageView) findViewById(R.id.img1);
        img2=(ImageView) findViewById(R.id.img2);
        scense=(ImageView)findViewById(R.id.scense);
        layout=(LinearLayout)findViewById(R.id.layout);
        layout.setOnClickListener(listener);
        layout1=(LinearLayout)findViewById(R.id.layout1);
        layout2=(LinearLayout)findViewById(R.id.layout2);
        res=getResources();
        script=res.getStringArray(R.array.scenes1);
        BT=new BitmapTool();
        role1=BT.NewImage(this,200,R.drawable.role1);
        role2=BT.NewImage(this,200,R.drawable.role2);
        other=BT.NewImage(this,200,android.R.drawable.ic_btn_speak_now);
        //scense.setBackgroundResource(R.drawable.animation_list);
//        testAnim=(AnimationDrawable)scense.getBackground();
////        testAnim.start();
        bg1=BT.NewImage(this,1080,R.drawable.test3);
        bg2=BT.NewImage(this,1080,R.drawable.test4);

        img1.setImageBitmap(bg1);

        setAnimation();
        //scense.setBackgroundResource(R.anim.animation_list);


    }

    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.layout:
                    talk();
                    break;

            }
        }
    };

    private void talk() {
        if (progress < script.length) {
            String line[]=script[progress].split(":");
            System.out.println(line[0]+":"+line[1]);
            if(ActiveLayout!=null){
                ActiveLayout.setAlpha(0.5f);
                ActiveLayout.setBackgroundResource(R.drawable.dialog_rectangle);


            }


            if (progress % 2 == 1) {
                TV_dialog = t1;
                TV_name=name1;
                IV_icon = img1;
                ActiveLayout = layout1;
            } else {
                TV_dialog = t2;
                TV_name=name2;
                IV_icon = img2;
                ActiveLayout = layout2;
            }
            TV_dialog.setText(script[progress]);
            TV_name.setText(line[0]);
            if(line[0].equals("火雞")){
            role_now=role1;
            }else if(line[0].equals("史提芬周")){
                role_now=role2;
            }else{role_now=other;}
            IV_icon.setImageBitmap(role_now);
            ActiveLayout.setAlpha(1.0f);
            ActiveLayout.setBackgroundResource(R.drawable.dialog_rectangle2);
            progress++;


        }else{
            Toast.makeText(getApplicationContext(),"第一篇講稿念完",Toast.LENGTH_SHORT).show();
        }
    }
    private void   setDrawableAnimation(){
        Anim = (AnimationDrawable) getResources()
                .getDrawable(R.drawable.animation_list);
        scense.setImageDrawable(Anim);
        Anim.start();

    }


    private void setAnimation(){
        Anim = new AnimationDrawable();
        Anim.setOneShot(false);  //true表示只循環一次

        Anim.addFrame(new BitmapDrawable(role1),200);
        Anim.addFrame(new BitmapDrawable(role2),200);
        img2.setImageDrawable(Anim);
        Anim.start();

        Anim2 = new AnimationDrawable();
        Anim2.setOneShot(false);  //true表示只循環一次

        Anim2.addFrame(new BitmapDrawable(bg1),200);
        Anim2.addFrame(new BitmapDrawable(bg2),200);

        img1.setImageDrawable(Anim2);
        Anim2.start();
    }

}
