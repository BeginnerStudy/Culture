package com.practice.culture.noUse;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.practice.culture.R;
import com.practice.culture.UI.BitmapTool;
import com.practice.culture.ChoiceWay;

public class PPT_OPActivity extends AppCompatActivity {
    ImageView ppt_image;
    Bitmap BgBitmap[];
    BitmapTool BT;
    AnimationDrawable Anim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ppt__op);
       //TextView
        TextView txt_press=findViewById(R.id.txt_press);
        ObjectAnimator animTxtAlpha =
                ObjectAnimator.ofFloat(txt_press, "alpha", 1, 0);
        animTxtAlpha.setDuration(1000);
        animTxtAlpha.setRepeatCount(-1);
        animTxtAlpha.setRepeatMode(ObjectAnimator.REVERSE);
        animTxtAlpha.setInterpolator(new LinearInterpolator());
        animTxtAlpha.start();

        //ImageView
        BT=new BitmapTool();
        ppt_image=(ImageView)findViewById(R.id.ppt_image);
        BgBitmap=new Bitmap[6];
        BgBitmap[0]=BT.NewImage(this,400,R.drawable.tzu_1);
        BgBitmap[1]=BT.NewImage(this,400,R.drawable.tzu_2);
        BgBitmap[2]=BT.NewImage(this,400,R.drawable.tzu_3);
        BgBitmap[3]=BT.NewImage(this,400,R.drawable.tzu_4);
        BgBitmap[4]=BT.NewImage(this,400,R.drawable.tzu_5);
        BgBitmap[5]=BT.NewImage(this,400,R.drawable.tzu_6);

        //ActionBar
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        //點擊事件
        LinearLayout PPT_View=findViewById(R.id.PPT_View);
        PPT_View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplication(), ChoiceWay.class);
                startActivity(i);
                finish();

            }
        });

        setAnimation();
    }
    private void setAnimation(){
        Anim = new AnimationDrawable();
        Anim.setOneShot(false);  //true表示只循環一次
        Anim.addFrame(new BitmapDrawable(BgBitmap[0]),500);
        Anim.addFrame(new BitmapDrawable(BgBitmap[1]),500);
        Anim.addFrame(new BitmapDrawable(BgBitmap[2]),500);
        Anim.addFrame(new BitmapDrawable(BgBitmap[3]),500);
        Anim.addFrame(new BitmapDrawable(BgBitmap[4]),500);
        Anim.addFrame(new BitmapDrawable(BgBitmap[5]),500);
        ppt_image.setImageDrawable(Anim);
        Anim.start();
    }
}
