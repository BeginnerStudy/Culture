package com.practice.culture;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.practice.culture.noUse.TWActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class BattleActivity extends AppCompatActivity implements  MediaPlayer.OnPreparedListener,MediaPlayer.OnErrorListener,MediaPlayer.OnCompletionListener{
    private FragmentManager FM=getSupportFragmentManager();
    private FragmentTransaction FT=FM.beginTransaction();
    private TextView EnemyTexts[];
    private TextView Battle_Msg;
    private ImageView MainRole_img;
    private ImageView EnemyImgs[];
    private Button btn_reset;
    private Button Act_Btn[]=new Button[4];
    private SharedPreferences SP,item;
    private int Rounds=1;//戰鬥回合數計算
    private boolean PlayerSelect=false;//用來確認目前是否在選取狀態
    private Unit Target_Select;//玩家指定的目標
    private Resources res;
    public MediaPlayer mMediaPlayer = null;
    private Boolean mbIsInitialised = true;
    private Uri uri;
    private TextView mainRole_Txt;
    private LinearLayout battleLayout;
    int MusicState;
    int ready=0,play=1,pause=2,stop=3,error=4;
    SoundPool soundPool;//戰鬥音效(未設定)
    Unit main;//主角

    ArrayList<Unit>Enemy=new ArrayList<>();
    ArrayList<Unit>Alian=new ArrayList<>();
    Random rnd=new Random();
    Map monsterlist;
    int BattleStage=0;//打算用一個變數來紀錄及判斷目前所處的狀態,例如0=準備,1=戰鬥中



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);
        res=getResources();
       init();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mMediaPlayer!=null && mbIsInitialised==false)
        {mMediaPlayer.start();}
    }

    @Override
    protected void onPostResume() {//Activity重起時要執行的事件
        super.onPostResume();

    }

    @Override
    protected void onStop() {//Activity停止時要做的處置
        super.onStop();
        Log.d("onStop","onStop");
        mMediaPlayer.stop();

        //SoundRelease();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
       //完成音樂播放時要做的事,這個案例暫時沒有特定事件要做
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        mMediaPlayer.release();
        MusicState=error;
        Toast.makeText(getApplicationContext(), "發生錯誤，停止播放", Toast.LENGTH_LONG)
                .show();
        return true;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mbIsInitialised=false;
        mp.seekTo(0);
        mp.start();
        MusicState=play;

    }

    public  class Unit extends  Role {//多設一個action用來判斷角色是否已行動,預設false代表未行動,true代表已行動
        boolean action;
        ImageView img;
        String ImageName;
        TextView text;
        boolean alive;

        public boolean isAlive() {
            return alive;
        }

        public void setAlive(boolean alive) {
            this.alive = alive;
        }

        public TextView getText() {
            return text;
        }

        public void setText(TextView text) {
            this.text = text;
        }

        public String getImageName() {
            return ImageName;
        }

        public void setImageName(String imageName) {
            ImageName = imageName;
        }

        public ImageView getImg() {
            return img;
        }

        public void setImg(ImageView img) {
            this.img = img;
        }

        public boolean getAction() {
            return action;
        }

        public void setAction(boolean action) {
            this.action = action;
        }

        public Unit(String name, int lv, int hp_now, int hp_max, int mp_now, int mp_max, int atk, int matk, int def, int agi, int state, boolean action, int exp,ImageView img,String ImageName,TextView text,boolean alive) {
            super(name, lv, hp_now, hp_max, mp_now, mp_max, atk, matk, def, agi, state, exp);
            this.action = action;
            this.img=img;
            this.ImageName=ImageName;
            this.text=text;
            this.alive=alive;

        }
        protected  void Attack(Unit Attacker ,Unit Attacked){

            //先設定2為倒下狀態
            String s="";//將執行結果以文字送回
            int damage=Math.max(Attacker.getAtk()-Attacked.getDef(),1);//先計算傷害,最少讓目標受到1點傷害
            if(Attacked.getState()!=2){//如果目標不是已死亡狀態,執行下列動作
                Attacked.setHp_now(Math.max(Attacked.hp_now-damage,0));//最低HP設為0
                Attacked.text.setText(Attacked.HPMP());
                s=Attacked.getName()+"受到了"+damage+"點傷害"+"\n";
                //之後應該要補上執行此動作的特效
                ObjectAnimator animator=ObjectAnimator.ofFloat(Attacked.getImg(),"rotationY",0,180,0);
                animator.setDuration(500);
                animator.start();


                //嘗試插入特效image
               // Attacked.img.setBackgroundResource(R.drawable.animation_list);
//                AnimationDrawable anim=R.drawable.animation_list;

//                ImageView effectImage=new ImageView(getApplicationContext());
//                effectImage.setLayoutParams(new LinearLayout.LayoutParams(100,100));
//                effectImage.setImageResource(R.mipmap.wilson);
//                battleLayout.addView(effectImage);



                //RelativeLayout.LayoutParams params =new RelativeLayout.LayoutParams(30,30);
//                params.leftMargin=100;
//                params.topMargin=100;

                if(Attacked.getHp_now()<=0){//如果被攻擊的目標死亡
                    Attacked.setAction(true);//設為已行動,避免如果以後有做復活術,當回合立刻打人
                    Attacked.setState(2);//暫時設2=死亡
                    Attacked.setAlive(false);
                    Attacked.img.setImageResource(R.mipmap.death);//改成暫時的死亡圖案
                    s=s+Attacked.getName()+"被擊倒了"+"\n";
                    if(Attacked==main){
                        GG();
                    }else if(Enemy.contains(Attacked)){
                        //設想是所有敵人都被幹掉,就結束戰鬥
                        boolean alive=true;
                        for(Unit unit:Enemy){
                            alive=unit.isAlive();

                            if(alive){
                            break;
                            }
                        }
                       if(!alive) {s=s+"擊敗所有敵人,結束戰鬥";
                       EndBattle();
                       }
                    }

                    }


            }else{//如果目標已經倒下,顯示攻擊失敗
                s="沒有擊中目標";
            }
            Battle_Msg.setText(s);
        }

    }


        private void init() {
            //先簡單測試註冊怪物
//            monsterlist = new HashMap<String, String>();
//            monsterlist.put("小拉達", "R.mipmap.pm019");
//            monsterlist.put("拉達", "R.mipmap.pm020");

            //設定音樂
            uri=Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.battle);
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setLooping(true);

            try {
                mMediaPlayer.setDataSource(this, uri);
                MusicState=ready;
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "指定的音樂檔錯誤！", Toast.LENGTH_SHORT).show();
            }


            mMediaPlayer.setOnPreparedListener(this);
            mMediaPlayer.setOnErrorListener(this);
            mMediaPlayer.setOnCompletionListener(this);
            //找view

            battleLayout = (LinearLayout) findViewById(R.id.Battle_Layout);
            battleLayout.setOnClickListener(onClickListener);
            EnemyImgs = new ImageView[3];
            EnemyImgs[0] = (ImageView) findViewById(R.id.Enemy_img1);
            EnemyImgs[1] = (ImageView) findViewById(R.id.Enemy_img2);
            EnemyImgs[2] = (ImageView) findViewById(R.id.Enemy_img3);
            for (ImageView img : EnemyImgs) {
                img.setOnClickListener(onClickListener);
            }
            mainRole_Txt=(TextView)findViewById(R.id.mainRole_Txt);
            MainRole_img=(ImageView)findViewById(R.id.mainRole_img);
            MainRole_img.setOnClickListener(onClickListener);
            EnemyTexts = new TextView[3];
            EnemyTexts[0] = (TextView) findViewById(R.id.Enemy_txt1);
            EnemyTexts[1] = (TextView) findViewById(R.id.Enemy_txt2);
            EnemyTexts[2] = (TextView) findViewById(R.id.Enemy_txt3);
            SP = getSharedPreferences("Player", MODE_PRIVATE);



            Act_Btn[0] = (Button) findViewById(R.id.btn_attack);
            Act_Btn[1] = (Button) findViewById(R.id.btn_item);
            Act_Btn[2] = (Button) findViewById(R.id.btn_run);
            Act_Btn[3] = (Button) findViewById(R.id.btn_state);
            btn_reset = (Button) findViewById(R.id.btn_reset);
            btn_reset.setOnClickListener(onClickListener);
            for (Button btn : Act_Btn) {
                btn.setOnClickListener(onClickListener);
            }
            Battle_Msg = (TextView) findViewById(R.id.battle_msg);

            EnemyCome();

        }

        private void EnemyCome() {
            mMediaPlayer.prepareAsync();
            Battle_Msg.setText("");
            //我軍初始化
            //為了方便大家參考,這邊直接列出我預設的等級一主角初始狀態
            //LV:1;HP:50;MP:10;ATK:10;MATK:10;DEF:0;AGI:10
            //目前是為了簡化,先用sharepreferences,如果有多個角色,可能要改成sqlite,如果有上傳到firebase的需求,最好再轉為JSON格式
            String Name = SP.getString("PlayerName", "玩家");

            int LV = SP.getInt("Role_lv", 1);
            int MaxHP = SP.getInt("Role_maxHP", 50);
            int HP = SP.getInt("Role_HP", MaxHP);//如果抓不到現存HP,就以MAXHP為準,說到底現在也沒講好HP要怎麼補,所以戰鬥結束後全滿也是一個選擇
            int MaxMP = SP.getInt("Role_maxMP", 10);
            int MP = SP.getInt("Role_MP", MaxMP);
            int ATK = SP.getInt("Role_ATK", 10);
            int MATK = SP.getInt("Role_MATK", 10);
            int DEF = SP.getInt("Role_DEF", 0);
            int AGI = SP.getInt("Role_AGI", 10);
            int EXP = SP.getInt("Role_EXP", 0);

            //Role main=new Role(Name,LV,HP,MaxHP,MP,MaxMP,ATK,MATK,DEF,AGI,0,0);
            main = new Unit(Name, LV, HP, MaxHP, MP, MaxMP, ATK, MATK, DEF, AGI, 0, false, EXP,MainRole_img,"gentleman",mainRole_Txt,true);
            int drawPath1=getResources().getIdentifier(main.getImageName(),"mipmap",getPackageName());
            main.img.setImageResource(drawPath1);
            mainRole_Txt.setText(main.HPMP());
            Alian.add(main);
            //敵人初始化
            if (!Enemy.isEmpty()) {
                Enemy.clear();
            }//清空
            int EnemyNum = rnd.nextInt(3) + 1;
            for (int i = 0; i < EnemyNum; i++) {
                //下面用預設值叫出滿狀態敵人,如果有特殊需求,也可以直接改參數,比如半血之類的
                Unit monster=null;
                double randomType=Math.random();
                if(randomType<0.4)
                { monster = new Unit("傲嬌妹", 1, 15, 15, 0, 0, 5, 0, 0, 5, 0, false, 0,EnemyImgs[i],"waifu_1",EnemyTexts[i],true);}
                else if(randomType>=0.4 && randomType<0.8){
                    monster = new Unit("黑長直", 1, 10, 10, 0, 0, 4, 0, 0, 11, 0, false, 0,EnemyImgs[i],"waifu_2",EnemyTexts[i],true);
                }else if(randomType>=0.8 ){
                    monster = new Unit("乖乖牌", 2, 30, 30, 10, 10, 8, 0, 2, 12, 0, false, 0,EnemyImgs[i],"waifu_3",EnemyTexts[i],true);
                }
                Enemy.add(monster);

                int drawPath2=getResources().getIdentifier(monster.getImageName(),"mipmap",getPackageName());

                monster.img.setImageResource(drawPath2);

                EnemyImgs[i].setVisibility(View.VISIBLE);
                EnemyTexts[i].setText(monster.getName() + "\t Lv:" + monster.getLv() + "\t HP:" + monster.getHp_now() + "/" + monster.getHp_max());
                EnemyTexts[i].setVisibility(View.VISIBLE);
                Battle_Msg.setText(Battle_Msg.getText() + monster.name + "出現了" + "\n");
            }
            Battle_Msg.setText(Battle_Msg.getText() + "戰鬥開始");
        }

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()) {
                    case R.id.Enemy_img1:

                        if(PlayerSelect==true && Enemy.get(0).isAlive()){
                            for(ImageView img:EnemyImgs)
                            {img.setBackgroundResource(R.drawable.shape_select);}
                            if(Target_Select!=Enemy.get(0))
                            {Target_Select=Enemy.get(0);
                                Target_Select.img.setBackgroundResource(R.drawable.shape_select2);
                            }else{//如果目標已經確認,因為目前只有主角一人,就應該直接進入戰鬥階段了\
                                BattleStage=1;
                                for(Button btn:Act_Btn){
                                    btn.setVisibility(View.INVISIBLE);
                                }
                                OrderCheck();
                            }



                        }
                        break;
                    case R.id.Enemy_img2:

                        if(PlayerSelect==true && Enemy.get(1).isAlive()){
                            for(ImageView img:EnemyImgs)
                            {img.setBackgroundResource(R.drawable.shape_select);}
                            if(Target_Select!=Enemy.get(1)) {
                                Target_Select=Enemy.get(1);
                                Target_Select.img.setBackgroundResource(R.drawable.shape_select2);
                            }
                            else{//如果目標已經確認,因為目前只有主角一人,就應該直接進入戰鬥階段了\
                                BattleStage=1;
                                for(Button btn:Act_Btn){
                                    btn.setVisibility(View.INVISIBLE);
                                }
                                OrderCheck();
                            }


                        }
                        break;
                    case R.id.Enemy_img3:

                        if(PlayerSelect==true && Enemy.get(2).isAlive()){
                            for(ImageView img:EnemyImgs)
                            {img.setBackgroundResource(R.drawable.shape_select);}
                            if(Target_Select!=Enemy.get(2)) {
                                Target_Select=Enemy.get(2);
                                Target_Select.img.setBackgroundResource(R.drawable.shape_select2);}

                                else{//如果目標已經確認,因為目前只有主角一人,就應該直接進入戰鬥階段了\
                                BattleStage=1;

                                for(Button btn:Act_Btn){
                                    btn.setVisibility(View.INVISIBLE);
                                }
                                OrderCheck();
                            }

                        }
                        break;
                    case R.id.btn_attack:
                        if(PlayerSelect==false)
                        {PlayerSelectTarget();//讓玩家選擇攻擊目標
                        Battle_Msg.setText("請選擇攻擊目標");
                        for(int x=0;x<Act_Btn.length;x++){
                            if(x==0){
                                Act_Btn[x].setText("取消");

                            }
                            else{ Act_Btn[x].setVisibility(View.INVISIBLE);}

                            }
                        }else{
                            for(int x=0;x<Act_Btn.length;x++){
                                for(ImageView img:EnemyImgs){
                                    img.setBackgroundResource(R.drawable.shape_select);
                                }
                                Act_Btn[x].setVisibility(View.VISIBLE);
                                if(x==0){
                                    Act_Btn[0].setText("攻擊");
                                }
                            }
                            PlayerSelect=false;
                        }

                        //OrderCheck();

                        break;
                    case R.id.btn_item:
                        break;
                    case R.id.btn_run:
                        //如果是固定事件,不允許逃跑
                        if (Math.random() < 0.5) {
                            //逃跑失敗,回合階段玩家行動喪失

                            Battle_Msg.setText("糟糕沒能溜掉");
                            main.setAction(true);
                            BattleStage=1;
                            OrderCheck();
                        } else {
                            //逃跑成功,脫離戰鬥
                            Battle_Msg.setText("順利脫離了戰鬥");
                        }
                        break;
                    case R.id.btn_state:
                        Battle_Msg.setText("");
                        for (Role role : Alian) {
                            String s = role.ReportState();

                            Battle_Msg.setText(s);

                        }
                        break;
                    case R.id.btn_reset:
                        Intent i = new Intent(BattleActivity.this, BattleActivity.class);
                        //mMediaPlayer.stop();
                        //mMediaPlayer.release();
                        startActivity(i);
                        finish();

                        break;
                    case R.id.Battle_Layout:
                        if(BattleStage==1){//如果在戰鬥中
                            OrderCheck();

                        }
                        break;

                }
            }
        };

        private void GG() {
            //因為目前只有主角一個角色,死了就可以直接GG,不用做後續判斷了
            Intent i=new Intent(getApplicationContext(), TWActivity.class);
            startActivity(i);
            finish();
        }

        //如果戰鬥尚未結束,且所有角色都已經進入state=1(已行動)時,回合結束
        //點擊執行下一個動作
        private void OrderCheck() {//回傳這回合要行動的角色
            //比較所有角色的agi值,判斷行動順序/可否行動
            if(PlayerSelect==true){PlayerSelect=false;}//把選擇狀態還原)
            Unit act = null;//下個要行動的角色變數
            ArrayList<Unit> All = new ArrayList<>();
            for (Unit unit : Alian) {
//            if(role.getHp_now()>0 && role.getState()==0){
//                All.add(role);//如果陣營角色hp>0,表示還活者,並且state=0(正常,未行動),則加入判斷序列
                if (unit.getAction() == false)
                    All.add(unit);
            }

            for (Unit unit : Enemy) {
//            if(role.getHp_now()>0 && role.getState()==0){
//                All.add(role);//一樣,換成敵隊而已
//            }
                if (unit.getAction() == false)
                    All.add(unit);

            }

            //雙方陣營,可動且未動的角色都加入All裡面了
            //下一步,找出agi最高的角色
            if (All.size() > 0) {
                for (int i = 0; i < All.size(); i++) {
                    if (i == 0) {
                        act = All.get(i);
                    }
                    if (act.getAgi() < All.get(i).getAgi()) {//如果agi比現在pick到的角色低,則將行動角色替換為這個角色
                        act = All.get(i);
                    } else if (act.getAgi() == All.get(i).getAgi()) {//如果agi相等,則50%機率替換
                        if (Math.random() < 0.5) {
                            act = All.get(i);
                        }
                    }
                }
                System.out.println(act.name);//只是拿來debug
                act.setAction(true);
                if(act==main){
                    act.Attack(act,Target_Select);
                }else if(Enemy.contains(act)==true){
                    act.Attack(act,main);
                }
                All.clear();

            } else {
                Rounds++;//回合數+1
                Battle_Msg.setText(Battle_Msg.getText()+"\n"+"第"+Rounds+"回合開始");
                //敵我雙方還活者的角色,將行動狀態設定為未行動,*特別指定只有活者的角色,是為了預備有復活的可能時,當回合不應該馬上能動
                for(Unit unit:Alian){
                    if(unit.getState()==0)
                    unit.setAction(false);}
                for(Unit unit:Enemy){
                    if(unit.getState()==0)
                        unit.setAction(false);}
                for(int i=0;i<Act_Btn.length;i++){
                    if(i==0){Act_Btn[i].setText("攻擊");}
                    Act_Btn[i].setVisibility(View.VISIBLE);
                }
                BattleStage=0;

            }
        }

        private void PlayerSelectTarget() {
            PlayerSelect=true;
            Target_Select=null;
            for(int i=0;i<Enemy.size();i++){
                //遍歷所有敵方單位,碰到的第一個活者的單位指定為目標
                if(Enemy.get(i).getState()==0){
                    Target_Select=Enemy.get(i);

                }
            }
        }

        private void selectTarget() {



        }

        private void Die(Role role) {
            //如果有角色死亡,就做一些對應事件


        }


        private void EndBattle() {
            //戰鬥結束後呼叫EndBattle方法,播放特效,執行對話,狀態清算.....然後回到地圖畫面
            int gainExp=0;
            int mainExp=main.getExp();
            for(Unit unit:Enemy){
                gainExp+=unit.getLv()*350;//測試升級,經驗先調超高
            }
            Battle_Msg.setText(Battle_Msg.getText()+"\n"+"獲得經驗值:"+gainExp+"\n");
            mainExp+=gainExp;
            //判斷是否升級
            if(mainExp>=500){
               int lvUP= mainExp/500;
               for(int i=1;i<=lvUP;i++){
                   main.LevelUP();
                   Battle_Msg.setText(Battle_Msg.getText()+"提升等級"+"\n");
               }
               mainExp%=500;
               main.setExp(mainExp);

            }
            //存檔
            SP.edit()
                    .putInt("Role_lv",main.getLv())
                    .putInt("Role_maxHP",main.getHp_max())
                    .putInt("Role_ATK",main.getAtk())
                    .putInt("Role_EXP",main.getExp())
                    .commit();
            Intent i =new Intent(getApplicationContext(),BattleActivity.class);
            startActivity(i);
            finish();






        }


    }
//想想,應該要將技能應該要在(角色)之下,變成是可以任何角色使用的方法,自由度會比較高
//含攻擊,道具在內,都應該考慮這樣設計,但暫時想不出好框架



