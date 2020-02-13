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
import com.practice.culture.R;

public class LoginActivity extends AppCompatActivity {
    EditText edit_Player;
    Button btn_send,btn_url;
    TextView txt_msg;
    ImageView img_data;
    ImageLoader imageLoader;
    DatabaseReference myRef;
    String caseUri;
    ConnectFireBase connectFireBase;
    DatabaseReference mRef;
    FirebaseDatabase database;
    SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value=dataSnapshot.getValue(String.class) ;
                Log.d("Read","Value is: " + value);
                for (int i=0;i<dataSnapshot.getChildrenCount();i++)
                {System.out.println(dataSnapshot.getValue());


                }
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    //adapter.add(ds.child("caseName").getValue().toString());

                    Log.d("DS",ds.getValue().toString()+"KEY:"+ds.getKey());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w("Cancel", "Failed to read value.", error.toException());

            }
        });




        init();

    }
    private void init(){
        //


        sp=getSharedPreferences("Player",MODE_PRIVATE);



        edit_Player=findViewById(R.id.edit_Player);
        edit_Player.setText(sp.getString("PlayerName","請輸入姓名"));

        edit_Player.setOnClickListener(listener);

        btn_send=findViewById(R.id.btn_sendName);
        btn_send.setOnClickListener(listener);
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
        //ListView listView=(ListView)findViewById(R.id.data_list);


        //測試firebase 資料庫
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference mRef=database.getReference();
//
//        mRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for(DataSnapshot ds:dataSnapshot.getChildren()){
//                    System.out.println("ALL="+ds.getKey()+ds.getValue());
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//
//
//        System.out.println("databaseref"+database.getReference());
//
//
//        myRef = database.getReference("99");
//
//        Log.w("myRefPath",String.valueOf(myRef.getPath()));
//
//        Log.d("refKey:",myRef.getKey()+"\"Parent:"+myRef.getParent()+"\"Root:"+myRef.getRoot()+"\"Path:"+myRef.getPath());
//
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                String value=dataSnapshot.getValue().toString() ;
//
//                Log.d("Read","Value is: " + value);
//
//                txt_msg.setText(value);
//                int i=0;
//                for(DataSnapshot ds:dataSnapshot.getChildren()){
//                    //adapter.add(ds.child("caseName").getValue().toString());
//                    i++;
//                    Log.d("DS","i=:"+i+"\\"+ds.getKey()+":"+ds.getValue().toString());
//                    if(ds.getKey().equals("caseName")){
//                        System.out.println("caseNamefind="+ds.getValue());
//                    }
//
//                }
//
////                for(int i=0;i<dataSnapshot.getChildrenCount();i++){
////
////                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                // Failed to read value
//                Log.w("Cancel", "Failed to read value.", error.toException());
//
//            }
//        });
//        DatabaseReference reference=FirebaseDatabase.getInstance().getReference("message");
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                adapter.clear();
//                for(DataSnapshot ds:dataSnapshot.getChildren()){
//                    //adapter.add(ds.child("caseName").getValue().toString());
//                    adapter.add(ds.getValue().toString());
//                    Log.d("DS",ds.getValue().toString());
//
//
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

    }

    private class ConnectFireBase extends Thread{


        @Override
        public void run() {
            super.run();

            try{
//                FirebaseDatabase database = FirebaseDatabase.getInstance();
////                DatabaseReference mRef=database.getReference();

//                mRef.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        for(DataSnapshot ds:dataSnapshot.getChildren()){
//                            System.out.println("ALL="+ds.getKey()+ds.getValue());
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });

//
//                System.out.println("databaseref"+database.getReference());


//                myRef = database.getReference("99");
//
//                Log.w("myRefPath",String.valueOf(myRef.getPath()));
//
//                Log.d("refKey:",myRef.getKey()+"\"Parent:"+myRef.getParent()+"\"Root:"+myRef.getRoot()+"\"Path:"+myRef.getPath());

                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String value=dataSnapshot.getValue().toString() ;

                        Log.d("Read","Value is: " + value);

                        txt_msg.setText(value);
                        int i=0;
                        for(DataSnapshot ds:dataSnapshot.getChildren()){
                            //adapter.add(ds.child("caseName").getValue().toString());
                            i++;
                            Log.d("DS","i=:"+i+"\\"+ds.getKey()+":"+ds.getValue().toString());
                            if(ds.getKey().equals("caseName")){
                                System.out.println("caseNamefind="+ds.getValue());
                            }

                        }

//                for(int i=0;i<dataSnapshot.getChildrenCount();i++){
//
//                }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Failed to read value
                        Log.w("Cancel", "Failed to read value.", error.toException());

                    }
                });

            }
            catch (Exception e){
                e.printStackTrace();
            }
            finally {

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


}
