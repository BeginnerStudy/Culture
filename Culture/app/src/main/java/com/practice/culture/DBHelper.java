package com.practice.culture;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private final static String DATABASE_NAME="CultureDB";//文化局DB
    private final static int DATABASE_VERSION = 1;



    private static final String TableName="CultrueDB";
    private static final String KEY_ID="_id";
    private final static String CaseID="caseID";
    private final static String CaseName="caseName";
    private final static String AssetsTypeName="assetsTypeName";
    private final static String PastHistory="pastHistory";
    private final static String GovInstitutionName="govInstitutionName";
    private final static String BelongAddress="belongAddress";
    private final static String BelongCity="belongCity";
    private final static String Longitude="longitude";
    private final static String Latitude="latitude";
    private final static String CaseUrl="caseUrl";
    private final static String BuildingFeatures="buildingFeatures";
    private final static String InHouseFeatures="inHouseFeatures";
    private final static String BuildingActualState="buildingActualState";
    private final static String BuildingUsage="buildingUsage";
    private final static String BuildingKeyMaintainItem="buildingKeyMaintainItem";





    public DBHelper(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String DATABASE_CREATE = "CREATE TABLE IF NOT EXISTS " + TableName + " (" +
                KEY_ID + " integer PRIMARY KEY autoincrement," +
                CaseID + "," +
                CaseName + "," +
                AssetsTypeName + "," +
               PastHistory+ ");";
        db.execSQL(DATABASE_CREATE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
