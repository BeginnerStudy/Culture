package com.practice.culture.UI;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

public class BitmapTool {
    private int GS=100;
    //預留BitmapTool設定大小的功能,以後再來實作,
    //可能的用法是,根據定義的最小畫面單位,動態改變轉圖 的大小
    public int getGS() {
        return GS;
    }

    public void setGS(int GS) {
        this.GS = GS;
    }

    public String bitmapToString(Bitmap bitmap) {
        String s = null;
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bs);
        byte[] bytes = bs.toByteArray();
        s = Base64.encodeToString(bytes, Base64.DEFAULT);
        return s;
    }

    public Bitmap stringToBitmap(String s) {
        Bitmap bitmap = null;
        try {
            byte[] bitmapArray;
            bitmapArray = Base64.decode(s, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);//
            System.out.println("字串轉bitmap成功");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("字串轉bitmap失敗");
        }
        return bitmap;


    }

    //傳入背景,要轉換的大小,圖片id,
    // 因為大家到時要處理的狀況不同,這邊就多型下去,其他人如果有其他要轉換的情況,
    // 自己順者往下加,跟其他人說明下就好

    public Bitmap NewImage(Context context, int size, int id){
        //context的輸入是關鍵,因為這整個JAVA檔只是一個類,沒有執行背景,是無法取得資源的
        Bitmap bitmap;
        //bitmap=BitmapFactory.decodeResource(context.getResources(),drawable);
        bitmap=BitmapFactory.decodeResource(context.getResources(),id);
        float scaleWidth=((float) size/bitmap.getWidth());
        float scaleHeight=((float) size/bitmap.getHeight());
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        bitmap=Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);

        return  bitmap;
    }
    public Bitmap NewImage(Context context,int size, Bitmap bitmap){

        float scaleWidth=((float) size/bitmap.getWidth());
        float scaleHeight=((float) size/bitmap.getHeight());
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        bitmap=Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);

        return  bitmap;
    }

}
