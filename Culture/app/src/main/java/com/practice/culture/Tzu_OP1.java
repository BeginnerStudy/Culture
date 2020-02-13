package com.practice.culture;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.practice.culture.noUse.Flipper;
import com.practice.culture.R;

public class Tzu_OP1 extends AppCompatActivity {
    ViewFlipper viewFlipper;
    Flipper flipper;
    MovieThread mvThread;
    TextView op_text;
    ObjectAnimator animTxtAlpha;
    Animation animation;
//    Canvas mCanvas;
//    Paint paint;
   // Animation

    @Override
    protected void onResume() {
        super.onResume();
//        mvThread = new MovieThread() ;
////        mvThread.start();
////        Log.d("Thread執行","Thread執行");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tzu__op1);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        op_text=findViewById(R.id.op_text);
        animTxtAlpha =
                ObjectAnimator.ofFloat(op_text, "alpha", 1, 0);
        animTxtAlpha.setDuration(1000);
        animTxtAlpha.setRepeatCount(-1);
        animTxtAlpha.setRepeatMode(ObjectAnimator.REVERSE);
        animTxtAlpha.setInterpolator(new LinearInterpolator());
        animTxtAlpha.start();
//        mCanvas=new Canvas();
//        paint.setColor(Color.RED);
//        paint.setTextSize(60f);

        //flipper=(Flipper) findViewById(R.id.flipper);
        viewFlipper=(ViewFlipper) findViewById(R.id.flipper);
        viewFlipper=(ViewFlipper) findViewById(R.id.flipper);
        viewFlipper.setOnClickListener(listener);
        //viewFlipper.setOnClickListener(listener);
        TypedArray OP_drawable=getResources().obtainTypedArray(R.array.OP_drawable);
        int OP_Pic[]=new int[OP_drawable.length()];
        for(int i=0;i<OP_drawable.length();i++){
            OP_Pic[i]=OP_drawable.getResourceId(i,0);

        }
        for(int j=0;j<OP_Pic.length;j++){
            //viewFlipper.addView(getImageView(OP_Pic[j]));
            viewFlipper.addView(getImageView(OP_Pic[j]));

        }
        viewFlipper.setInAnimation(this,R.anim.slide_in);

        //viewFlipper.setInAnimation(this,R.anim.right_in);//圖片進入效果
        viewFlipper.setOutAnimation(this,R.anim.left_out);//圖片出去效果
        viewFlipper.setFlipInterval(5000);//圖片切換時間間隔
        viewFlipper.startFlipping();

        //animation=viewFlipper.getAnimation();
        animation=viewFlipper.getOutAnimation();
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                System.out.println("目前的動畫是"+viewFlipper.getDisplayedChild());
                if(viewFlipper.getDisplayedChild()==2){
                    EndOP();

                    System.out.println("偵測到動畫結束");
                }

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });












    }

    @Override
    protected void onStop() {
        super.onStop();
//        mvThread.interrupt();
    }

    View.OnClickListener listener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.flipper:

                    EndOP();
                    Intent i=new Intent(getApplication(),ChoiceWay.class);
                    startActivity(i);
                    break;
            }

        }
    };

    private ImageView getImageView(int new_photo){
        ImageView view = new ImageView(this);
        view.setImageResource(new_photo);
        return view;
    }
    private class MovieThread extends  Thread {
        public void run() {
            super.run();

            try{
             sleep(15000);

            }catch (InterruptedException e){
                e.printStackTrace();
                Log.d("錯誤","錯誤發生");

            }
            finally {EndOP();
                Log.d("thread結束","thread end");

            }



        }
    }

    public void EndOP(){
        viewFlipper.stopFlipping();
        viewFlipper.clearAnimation();
        viewFlipper.setDisplayedChild(0);

        animTxtAlpha.pause();
        op_text.setVisibility(View.GONE);

    }


    }
