package com.meima.ui;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

 class ContentAdapter extends ArrayAdapter<Content> {
	 
    private List<Content> items;
 
    public ContentAdapter(Context context, int textViewResourceId, 
            List<Content> items) { 
        super(context, textViewResourceId, items); 
        this.items = items; 
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) { 
        View view = convertView;
 
        if (view == null) { 
            //LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
            //view = inflater.inflate(R.layout.main_list, null); 
        }
 
        Content content = items.get(position); 
        if (content != null) { 
            //TextView title = (TextView) view 
           //         .findViewById(R.id.ContentTitle); 
           // TextView comment = (TextView) view 
            //        .findViewById(R.id.ContentComment); 
           // ImageView imageView = (ImageView) view.findViewById(R.id.image);
 
           // title.setText(content.getTitle()); 
            //comment.setText(content.getComment()); 
            //imageView.setImageDrawable(MainWeixin.this.getResources() 
              //      .getDrawable(Integer.parseInt(content.getImageUrl()))); 
        }
 
        return view; 
    } 
}