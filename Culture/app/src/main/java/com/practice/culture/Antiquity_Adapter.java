package com.practice.culture;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class Antiquity_Adapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<Antiquity> itemList;

    public Antiquity_Adapter(ArrayList<Antiquity>itemlist, Context context){
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.itemList = itemlist;

    }
    public void setInflater(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        if (itemList !=null){
            return itemList.size();
        }else {
            return 0;
        }//因為一定要回傳一個整數,所以有資料時回傳資料筆數,沒資料也要回傳0
    }

    @Override
    public Object getItem(int position) {
        if (itemList !=null)
            return itemList.get(position);
        return null;
    }

    @Override
    public long getItemId(int position) {
        if (itemList != null)
            return itemList.get(position).hashCode();
        else
            return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=inflater.inflate(R.layout.data_layout,null);
        TextView Antiquity_Name=convertView.findViewById(R.id.Antiquity_Name);
        TextView Antiquity_Address=convertView.findViewById(R.id.Antiquity_Address);
        TextView Antiquity_Type=convertView.findViewById(R.id.Antiquity_Type);
        ImageView Antiquity_Image=convertView.findViewById(R.id.Antiquity_Image);


        Antiquity antiquity=itemList.get(position);
        String name=antiquity.getCaseName();
        String address=antiquity.getBelongAddress();
        String type=antiquity.getAssetsTypeName();
        String picURI=antiquity.getRepresentImage();



        Antiquity_Name.setText(name);
        Antiquity_Address.setText(address);
        Antiquity_Type.setText(type);
        if(!picURI.equals("無資料")){
        ImageLoader imageLoader=ImageLoader.getInstance();
        imageLoader.displayImage(antiquity.getRepresentImage(),Antiquity_Image);}else{
            Antiquity_Image.setImageResource(R.drawable.no_image);
        }



        return convertView;
    }
    public void setItemList(ArrayList<Antiquity> itemList) {
        this.itemList = itemList;
    }
    public List<Antiquity> getItemList() {
        return itemList;
    }

}
