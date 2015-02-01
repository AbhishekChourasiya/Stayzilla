package com.example.computers.verify;

import android.graphics.Bitmap;

/**
 * Created by Aman on 1/11/2015.
 */
public class RowItem {

    private String name;
    private String displayName;
    private String stayType;
    private String address;
    private String starRating;
    private String price;
    private String id;
    private Bitmap profile_pic_id;
    //private String status;
    //private String contactType;
    private int position;
    public RowItem(String displayName,Bitmap profile_pic_id
                   , String address, String price, String starRating) {


        this.displayName = displayName;
        this.profile_pic_id = profile_pic_id;
        this.address = address;

        this.price = price;
        this.starRating=starRating;

    }



    public String getDisplayName() {
        return displayName;
    }
    public int getPosition(){
        return position;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address =address;
    }

    public String getPrice() {
        return price;
    }
    public Bitmap getProfile_pic_id() {
        return profile_pic_id;
    }

    public void setProfile_pic_id(Bitmap profile_pic_id) {
        this.profile_pic_id = profile_pic_id;
    }
    public void setPrice(String price) {
        this.price=price;
    }

    public String getStarRating() {
        return starRating;
    }

    public void setStarRating(String starRating) {
        this.starRating=starRating;
    }


}