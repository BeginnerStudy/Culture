package com.practice.culture;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

public class AnimOp extends AppCompatActivity {
    VideoView videoView;
    ActionBar actionBar;
    TextView txt_press;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_op);
        Uri uri=Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.onepunch);
        actionBar=getSupportActionBar();
        actionBar.hide();
        txt_press=findViewById(R.id.txt_press);
        ObjectAnimator animTxtAlpha =
                ObjectAnimator.ofFloat(txt_press, "alpha", 1, 0);
        animTxtAlpha.setDuration(1000);
        animTxtAlpha.setRepeatCount(-1);
        animTxtAlpha.setRepeatMode(ObjectAnimator.REVERSE);
        animTxtAlpha.setInterpolator(new LinearInterpolator());
        animTxtAlpha.start();
        MediaController mediaController=new MediaController(this);
        mediaController.setVisibility(View.GONE);
        mediaController.setAnchorView(videoView);
        videoView=findViewById(R.id.videoView);
        videoView.setMediaController(mediaController);



        RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.FILL_PARENT,RelativeLayout.LayoutParams.FILL_PARENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        videoView.setLayoutParams(layoutParams);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();
        videoView.canPause();


        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Intent i=new Intent(getApplication(), MainActivity.class);
                startActivity(i);
                finish();

            }
        });
        videoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(getApplication(), ChoiceWay.class);
                startActivity(i);
                finish();
            }
        });


}

    @Override
    protected void onResume() {

        super.onResume();
        System.out.println("onResume");
        videoView.resume();
        videoView.start();
    }



    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("onStop");
        videoView.pause();
    }
}
