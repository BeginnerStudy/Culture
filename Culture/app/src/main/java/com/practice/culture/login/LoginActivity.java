package com.practice.culture.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.practice.culture.Antiquity;
import com.practice.culture.Antiquity_Adapter;
import com.practice.culture.BattleActivity;
import com.practice.culture.R;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    static boolean updated=false;
    EditText edit_Player,edit_ref;
    Button btn_send,btn_url,btn_search,btn_jump;
    TextView txt_msg;
    ImageView img_data;

    DatabaseReference myRef;
    String caseUri;
    ConnectFireBase connectFireBase;
    DatabaseReference mRef;
    FirebaseDatabase database;
    SharedPreferences sp;

    ArrayList<Antiquity>Antiquitys=new ArrayList<>();
    private Antiquity_Adapter adapter;
    private ListView list=null;
    //ArrayAdapter<Antiquity>Antiquitys;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));

        init();

    }
    private void init(){
        //

       list=(ListView)findViewById(R.id.listView);


        sp=getSharedPreferences("Player",MODE_PRIVATE);



        edit_Player=findViewById(R.id.edit_Player);
        edit_Player.setText(sp.getString("PlayerName","請輸入姓名"));
        edit_ref=(EditText)findViewById(R.id.edit_ref);
        edit_Player.setOnClickListener(listener);

        btn_send=findViewById(R.id.btn_sendName);
        btn_send.setOnClickListener(listener);
        btn_search=(Button)findViewById(R.id.btn_search2);
        btn_search.setOnClickListener(listener);
        btn_jump=(Button)findViewById(R.id.btn_jump);
        btn_jump.setOnClickListener(listener);
        txt_msg=findViewById(R.id.txt_mseeage);
        img_data=findViewById(R.id.img_data);
        btn_url=findViewById(R.id.btn_url);
        btn_url.setOnClickListener(listener);
        ImageLoader imageLoader=ImageLoader.getInstance();
        imageLoader.displayImage("https://data.boch.gov.tw/old_upload/_upload/Assets_new/building/39226/photo/pic35.jpg",img_data);
        database = FirebaseDatabase.getInstance();
        DatabaseReference mRef=database.getReference();
        connectFireBase=new ConnectFireBase();
        connectFireBase.start();
        myRef=database.getReference("99");


    }

    private class ConnectFireBase extends Thread{


        @Override
        public void run() {
            super.run();
            //應在開啟程式時執行一次更新動作,用靜態變數存取是否有更新過資料
            // 應該加入while判斷是否有下一筆,如果資料抓完了,就關閉thread並宣告已更新完成
            //try下面紙應該一次回一筆

                try {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference mRef = database.getReference();

                    mRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            String childREF[] = new String[]{"caseID", "caseName", "assetsTypeName", "pastHistory", "govInstitutionName", "belongAddress",
                                    "belongCity", "longitude", "latitude", "caseUrl", "buildingFeatures", "inHouseFeatures", "buildingActualState", "buildingUsage", "buildingKeyMaintainItem", "representImage"};
                            String result[] = new String[childREF.length];

                            for (DataSnapshot ds : dataSnapshot.getChildren()) {

                                if (ds.getKey() != "message") {
                                    result[0] = ds.getKey();

                                    for (int i = 1; i < childREF.length; i++) {
                                        if (i == 0) {
                                            result[0] = ds.getKey();
                                        }
                                        if (ds.hasChild(childREF[i])) {
                                            result[i] = ds.child(childREF[i]).getValue().toString();
                                        } else {
                                            result[i] = "無資料";
                                        }
                                        //System.out.println("存入" + childREF[i] + "/" + result[i]);
                                    }
                                    Antiquity a = new Antiquity(result[0], result[1], result[2],
                                            result[3], result[4], result[5], result[6], result[7], result[8], result[9], result[10],
                                            result[11], result[12], result[13], result[14], result[15]);
                                    Antiquitys.add(a);
                                   // System.out.println("案名" + ds.child("caseName").getKey() + "/" + ds.child("caseName").getValue());
//                            System.out.println("子項目有:"+ds.getChildrenCount());
//                            System.out.println("ALL-KEY="+ds.getKey()+"Value="+ds.getValue());
                                }
                            }

                            System.out.println("資料更新done");
                            adapter = new Antiquity_Adapter(Antiquitys, LoginActivity.this);
                            list.setAdapter(adapter);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {

                }
            }
        }


    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.edit_Player:
                    edit_Player.setText("");
                    break;
                case R.id.btn_sendName:
                    //String s=edit_Player.getText().toString();
                    if(!TextUtils.isEmpty(edit_Player.getText().toString())  ) {//&& edit_Player.getText().toString()!="請輸入姓名"
                    sp.edit().putString("PlayerName",edit_Player.getText().toString()).commit();
                    Toast.makeText(getApplication(),"姓名存檔"+sp.getString("PlayerName","Null"),Toast.LENGTH_SHORT).show();
                    }
                        else{Toast.makeText(getApplication(),"error",Toast.LENGTH_SHORT).show();}


                    break;
                case R.id.btn_url:
//                   String s=loadData();
//                   Log.d("uriget",s);
                    loadData();
                   Uri uri=Uri.parse("http://nchdb.boch.gov.tw/assets/overview/monument/19920110000002");
                   Intent i=new Intent(Intent.ACTION_VIEW,uri);
                   startActivity(i);
                    //Uri uri=Uri.parse(myRef.getKey());
                    break;
                case R.id.btn_search2:
                    SearchResult();
                    break;
                case R.id.btn_jump:
                    Intent i2=new Intent(getApplicationContext(), BattleActivity.class);
                    startActivity(i2);
                    break;

            }

        }
    };

    private String loadData(){

        Query dataQuery=myRef.child("caseUrl");
        dataQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    System.out.println("Loaddata="+dataSnapshot.getValue());
                    caseUri=String.valueOf(dataSnapshot.getValue());

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return  caseUri;
    }

    private void SearchResult(){
        String searchID=edit_ref.getText().toString();
        System.out.println(searchID);
        if(!TextUtils.isEmpty(searchID)){
            for(Antiquity data:Antiquitys){
                if(data.getCaseID().equals(searchID)){
                    String s=data.getCaseID()+"\n"+data.getCaseName()+"\n"+data.getAssetsTypeName()+"\n"+data.getPastHistory()+
                            "\n"+data.getGovInstitutionName()+"\n"+data.getBelongAddress()+"\n"+data.getBelongCity()+"\n"+
                            data.getLongitude()+"\n"+data.getLatitude()+"\n"+data.getCaseUrl()+"\n"+data.getBuildingFeatures()+"\n"+
                            data.getInHouseFeatures()+"\n"+data.getBuildingActualState()+"\n"+data.getBuildingUsage()+"\n"+data.getBuildingKeyMaintainItem();
                    txt_msg.setText(s);

                    ImageLoader imageLoader = ImageLoader.getInstance();
                    if(!data.getRepresentImage().equals("無資料"))
                    {imageLoader.displayImage(data.getRepresentImage(),img_data);}
                    else{img_data.setImageResource(R.drawable.no_image);}
                    System.out.println(s);
                    break;
                }

            }

        }else{Toast.makeText(LoginActivity.this,"搜尋值null",Toast.LENGTH_SHORT).show();

        }

    }


}
