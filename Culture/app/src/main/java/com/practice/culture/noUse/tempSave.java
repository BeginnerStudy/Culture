package com.practice.culture.noUse;

public class tempSave {

    //FirebaseDatabase database = FirebaseDatabase.getInstance();

//        DatabaseReference myRef = database.getReference("message");
//        myRef.setValue("Hello, World!");

//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                String value=dataSnapshot.getValue(String.class) ;
//                Log.d("Read","Value is: " + value);
//                for (int i=0;i<dataSnapshot.getChildrenCount();i++)
//                {System.out.println("ReadTEST"+dataSnapshot.getValue());
//
//
//                }
//                for(DataSnapshot ds:dataSnapshot.getChildren()){
//                    //adapter.add(ds.child("caseName").getValue().toString());
//
//                    Log.d("DS",ds.getValue().toString()+"KEY:"+ds.getKey());
//                }
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
//    String CaseID="null";
//    String CaseName="null";
//    String AssetsTypeName ="null";
//    String PastHistory = "null";
//    String GovInstitutionName = "null";
//    String BelongAddress = "null";
//    String BelongCity = "null";
//    String Longitude = "null";
//    String Latitude =  "null";
//    String CaseUrl ="null";
//    String BuildingFeatures = "null";
//    String InHouseFeatures = "null";
//    String BuildingActualState ="null";
//    String BuildingUsage ="null";
//    String BuildingKeyMaintainItem = "null";
    //                            Antiquitys.add(new Antiquity(CaseID,CaseName,AssetsTypeName,PastHistory,GovInstitutionName,BelongAddress,BelongCity,
//                                    Longitude,Latitude,CaseUrl,BuildingFeatures,InHouseFeatures,BuildingActualState,BuildingUsage,BuildingKeyMaintainItem));
    {//                            CaseName=ds.child("caseName").getValue().toString();

        //AssetsTypeName = ds.child("assetsTypeName").getValue().toString();
//                            PastHistory = ds.child("pastHistory").getValue().toString();
//                            GovInstitutionName = ds.child("govInstitutionName").getValue().toString();
//                            BelongAddress = ds.child("belongAddress").getValue().toString();
//                            BelongCity = ds.child("belongCity").getValue().toString();
//                            Longitude = ds.child("longitude").getValue().toString();
//                            Latitude =   ds.child("latitude").getValue().toString() ;
//                            CaseUrl = ds.child("caseUrl").getValue().toString();
//                            BuildingFeatures = ds.child("buildingFeatures").getValue().toString();
//                            InHouseFeatures = ds.child("inHouseFeatures").getValue().toString();
//                            BuildingActualState =ds.child("buildingActualState").getValue().toString();
//                            BuildingUsage = ds.child("buildingUsage").getValue().toString();
//                            BuildingKeyMaintainItem = ds.child("buildingKeyMaintainItem").getValue().toString();
    }

//     System.out.println("databaseref"+database.getReference());
//
//
//    myRef = database.getReference("99");
//
//                Log.w("myRefPath",String.valueOf(myRef.getPath()));
//
//                Log.d("refKey:",myRef.getKey()+"\"Parent:"+myRef.getParent()+"\"Root:"+myRef.getRoot()+"\"Path:"+myRef.getPath());
//
//                myRef.addValueEventListener(new ValueEventListener() {
//        @Override
//        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//            String value=dataSnapshot.getValue().toString() ;
//
//            Log.d("Read","Value is: " + value);
//
//            txt_msg.setText(value);
//            int i=0;
//            for(DataSnapshot ds:dataSnapshot.getChildren()){
//                //adapter.add(ds.child("caseName").getValue().toString());
//                i++;
//                Log.d("DS","i=:"+i+"\\"+ds.getKey()+":"+ds.getValue().toString());
//                if(ds.getKey().equals("caseName")){
//                    System.out.println("caseNamefind="+ds.getValue());
//                }
//            }
//        }
//        @Override
//        public void onCancelled(@NonNull DatabaseError error) {
//            // Failed to read value
//            Log.w("Cancel", "Failed to read value.", error.toException());
//        }
//    });

}
