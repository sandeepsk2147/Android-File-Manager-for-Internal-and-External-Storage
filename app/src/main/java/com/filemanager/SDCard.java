package com.filemanager;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;



public class SDCard extends Fragment implements CustomAdapter.customButtonListener {
    @Override
    public void onButtonClickListner(int position) {
        ItemObject obj = allItems.get(position);

        if (obj.getMusicAuthor().equals("fl")) {

            new LD(obj.getMusicName(), false).execute();

        } else {
            String filePath = obj.getPath();
            Toast.makeText(getActivity(),filePath,Toast.LENGTH_LONG).show();


        }
    }

    public SDCard() {
    }

    Files fl = new Files(false);
    CustomAdapter customAdapter;
    List<ItemObject> allItems = new ArrayList<ItemObject>();
    GridView gridview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.storage_layout, container, false);
        gridview = (GridView) view.findViewById(R.id.gridview);


        customAdapter = new CustomAdapter(getActivity(), allItems);
        gridview.setAdapter(customAdapter);
        if (fl.isCard()) {
            new LD("", true).execute();
        }
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //  Toast.makeText(getActivity(), "Position: " + position, Toast.LENGTH_SHORT).show();


            }
        });
        return view;
    }

    private void load(String name, boolean isk) {


    }

    private class LD extends AsyncTask<Void, Void, Void> {
        ProgressDialog dg;
        String name;
        boolean isk;

        public LD(String name, boolean isk) {
            this.name = name;
            this.isk = isk;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dg = new ProgressDialog(getActivity());
            dg.setMessage("Loading...");
            dg.setCancelable(false);
            dg.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dg.dismiss();
            if (allItems != null) {
                customAdapter = new CustomAdapter(getActivity(), allItems);
                customAdapter.setCustomButtonListner(SDCard.this);
                gridview.setAdapter(customAdapter);
                customAdapter.setCustomButtonListner(SDCard.this);
            }
        }

        @Override
        protected Void doInBackground(Void... params) {
            allItems.clear();
            allItems = fl.getFolder(name, isk);

            return null;
        }
    }

}