package com.practice.culture;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.style.BackgroundColorSpan;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;

import org.json.JSONObject;

public class Role {

    String name;
    int lv;
    int hp_now;
    int hp_max;
    int mp_now;
    int mp_max;
    int atk;
    int matk;//目前還沒有實際效果的數值(因為還沒設計好法術),
    int def;
    int agi;


    //int gold;//基本上是拿來設定打倒後掉落金錢用的,不過現在好像也還沒弄出金錢的實際作用...我比較傾向收集某個值的概念(靈力阿 記憶碎片...之類
    int state=0;//state 0=正常,1=結束行動,2=倒下
    //可能要加入一個position來方便撥放特效
    //比如說固定主角1 怪max3,就可以在呼叫或創建role的時候,直接指定ImageView當作位置
    int exp;//作為升級之依據....建議用固定上限,比如說固定達500時提昇等級,取得的經驗值則依照敵我等級差計算,如此一來小怪經驗直接=0,有等級即可

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getMatk() {
        return matk;
    }

    public void setMatk(int matk) {
        this.matk = matk;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLv() {
        return lv;
    }

    public void setLv(int lv) {
        this.lv = lv;
    }

    public int getHp_now() {
        return hp_now;
    }

    public void setHp_now(int hp_now) {
        this.hp_now = hp_now;
    }

    public int getHp_max() {
        return hp_max;
    }

    public void setHp_max(int hp_max) {
        this.hp_max = hp_max;
    }

    public int getMp_now() {
        return mp_now;
    }

    public void setMp_now(int mp_now) {
        this.mp_now = mp_now;
    }

    public int getMp_max() {
        return mp_max;
    }

    public void setMp_max(int mp_max) {
        this.mp_max = mp_max;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getAgi() {
        return agi;
    }

    public void setAgi(int agi) {
        this.agi = agi;
    }

//    public int getGold() {
//        return gold;
//    }

//    public void setGold(int gold) {
//        this.gold = gold;
//    }

    public Role(String name, int lv, int hp_now, int hp_max, int mp_now, int mp_max, int atk,int matk, int def, int agi,int state,int exp) {
        this.name = name;

        this.lv = lv;
        this.hp_now = hp_now;
        this.hp_max = hp_max;
        this.mp_now = mp_now;
        this.mp_max = mp_max;
        this.atk = atk;
        this.matk = matk;
        this.def = def;
        this.agi = agi;
        //this.gold = gold;
        this.state = state;
        this.exp = exp;

    }
    public void injury(Role role,Role attacker){
        int hp=role.getHp_now();
        int def=role.getDef();
        int atk=attacker.getAtk();
        int danage=atk-def;
        hp= Math.min(hp-danage,0);
    }
    protected void LevelUP(){
        //升級要做的事放在這邊
        this.setLv(lv+1);//每run一次升級執行一次
        //這個嘛...一樣是沒講好參數,就先隨便升2種數值就好
        this.setAtk(this.getAtk()+10);
        this.setHp_max(this.getHp_max()+50);


    }
    public String HPMP(){
        String s="";
        Role role=this;
        s="HP:"+role.getHp_now()+"/"+role.getHp_max()+"\n"
          +"MP:"+role.getMp_now()+"/"+role.getMp_max();
        return s;
    }
    public String ReportState(){
        Role role=this;
        String s="";
        s="LV:"+role.getLv()+"\n"+"Name:"+role.getName()+"\n"
            +"HP:"+role.getHp_now()+"/"+role.getHp_max()+"\n"
                + "ATK:"+role.getAtk()+"\n"+"MATK"+role.getMatk()+"\n"+"DEF:"+role.getDef()+"\n"+"AGI:"+role.getAgi()+"\n"+"EXP:"+role.getExp();
        return  s;
    }


    //等格式確定好,做一個方法,將角色的現在狀況存為JSON格式回傳
//    private JSONObject RoleJson(Role role){
//
//
//
//    }


}
