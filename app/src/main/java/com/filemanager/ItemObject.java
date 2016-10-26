package com.filemanager;

import android.graphics.Bitmap;

public class ItemObject {

    private int screenShot;
    private String musicName;
    private String musicAuthor;
    private String path;
    private Bitmap map=null;

    public ItemObject(int screenShot, String musicName, String musicAuthor,String path) {
        this.screenShot = screenShot;
        this.musicName = musicName;
        this.musicAuthor = musicAuthor;
        this.path=path;
    }

    public  void setBitmap(Bitmap map){
        this.map=map;
    }
    public Bitmap getBitmap(){
        return this.map;
    }

    public int getScreenShot() {
        return screenShot;
    }

    public String getMusicName() {
        return musicName;
    }

    public String getMusicAuthor() {
        return musicAuthor;
    }
    public String getPath(){return this.path;}
}

