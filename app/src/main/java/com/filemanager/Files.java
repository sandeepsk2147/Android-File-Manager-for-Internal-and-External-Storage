package com.filemanager;

import android.os.Environment;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by vccc on 10/26/2016.
 */
public class Files
{

    public String filePath="";
    private String ext;
    public Files(boolean isk){
        if(isk){
            filePath =  Environment.getExternalStorageDirectory().toString();// Load Internal Storage
        }
        else{
            filePath=System.getenv("SECONDARY_STORAGE"); // Load SD card or External Storage
        }
    }
    public boolean isCard(){
        return new File(System.getenv("SECONDARY_STORAGE")).exists();
    }


    public ArrayList<ItemObject> getFolder(String fll, boolean isk){
        try {
            ArrayList<ItemObject> folder = new ArrayList<ItemObject>();
            ItemObject obj;
            File f;
            if (isk)
                f = new File(filePath);
            else {
                filePath = filePath + File.separator + fll;
                f = new File(filePath);
            }

            File[] files = f.listFiles();
            for (File inFile : files) {

                if (inFile.isDirectory()) {
                    // is directory
                    folder.add(new ItemObject(R.drawable.folder, inFile.getName(), "fl", ""));

                } else if (inFile.isFile()) {
                    ext = getFileExt(inFile.getName()).toLowerCase();
                    if (ext.equals("jpg") || ext.equals("jpeg") || ext.equals("png") || ext.equals("gif") || ext.equals("bmp")) {
                        obj = new ItemObject(R.drawable.img, inFile.getName(), "", inFile.getAbsolutePath());
                        //obj.setBitmap(nn.resizeImage(nn.decodeSampledBitmapFromResource(obj.getPath(),80,80)));
                        folder.add(obj);
                    } else if (ext.equals("mp3") || ext.equals("ogg") || ext.equals("aac") || ext.equals("wav") || ext.equals("aac")) {
                        folder.add(new ItemObject(R.drawable.mp3, inFile.getName(), "", inFile.getAbsolutePath()));

                    } else if (ext.equals("mp4") || ext.equals("flv") || ext.equals("wmv") || ext.equals("mpeg") || ext.equals("3gp")) {
                        folder.add(new ItemObject(R.drawable.video, inFile.getName(), "", inFile.getAbsolutePath()));

                    } else if (ext.equals("doc") || ext.equals("docm") || ext.equals("docx")) {
                        folder.add(new ItemObject(R.drawable.word, inFile.getName(), "", inFile.getAbsolutePath()));

                    } else if (ext.equals("pdf")) {
                        folder.add(new ItemObject(R.drawable.pdf, inFile.getName(), "", inFile.getAbsolutePath()));

                    } else if (ext.equals("apk")) {
                        folder.add(new ItemObject(R.drawable.apk, inFile.getName(), "", inFile.getAbsolutePath()));

                    } else {
                        folder.add(new ItemObject(R.drawable.blank, inFile.getName(), "", inFile.getAbsolutePath()));

                    }
                }
            }
            return folder;
        }catch(Exception e){return null;}
    }

    public  String getFileExt(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).toLowerCase();
    }
}
