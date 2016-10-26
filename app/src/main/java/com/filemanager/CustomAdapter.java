package com.filemanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CustomAdapter extends BaseAdapter {

    private LayoutInflater layoutinflater;
    private List<ItemObject> listStorage;
    private Context context;
    private customButtonListener customListner;
    public CustomAdapter(Context context, List<ItemObject> customizedListView) {
        this.context = context;
        layoutinflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        listStorage = customizedListView;
    }
    public interface customButtonListener {
        public void onButtonClickListner(int position);
    }
    public void setCustomButtonListner(customButtonListener listener) {
        this.customListner = listener;
    }
    @Override
    public int getCount() {
        return listStorage.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder listViewHolder;
        if(convertView == null){
            listViewHolder = new ViewHolder();
            convertView = layoutinflater.inflate(R.layout.row, parent, false);
            listViewHolder.screenShot = (ImageView)convertView.findViewById(R.id.screen_shot);
            listViewHolder.musicName = (TextView)convertView.findViewById(R.id.music_name);
            listViewHolder.musicAuthor = (TextView)convertView.findViewById(R.id.music_author);
            listViewHolder.path=(TextView)convertView.findViewById(R.id.path);
            convertView.setTag(listViewHolder);
        }else{
            listViewHolder = (ViewHolder)convertView.getTag();
        }
        listViewHolder.screenShot.setImageResource(listStorage.get(position).getScreenShot());
        listViewHolder.musicName.setText(listStorage.get(position).getMusicName());
        listViewHolder.path.setText(listStorage.get(position).getPath());

        listViewHolder.musicAuthor.setText(listStorage.get(position).getMusicAuthor());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (customListner != null) {

                    customListner.onButtonClickListner(position);
                }
            }
        });
        return convertView;
    }

    static class ViewHolder{
        ImageView screenShot;
        TextView musicName;
        TextView musicAuthor,path;
    }

}
