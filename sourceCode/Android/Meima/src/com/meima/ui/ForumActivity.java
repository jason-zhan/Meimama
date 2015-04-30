package com.meima.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.meima.R;
import com.meima.ui.MainWeixin.MyAdapter;
import com.meima.ui.MainWeixin.ViewHolder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ForumActivity extends Activity implements OnClickListener{

	private List<Map<String, Object>> data;
    private ListView lv;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forum_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN); 
        data = getData();
        MyAdapter adapter = new MyAdapter(this, data);
        lv = (ListView)findViewById(R.id.forum_tab);
        lv.setAdapter(adapter);
    }
	
	 private List<Map<String, Object>> getData()
	    {
	        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	        String [] titleStr = {"人性的毁灭","新手报道","生病中","好无聊啊","这是骗子","时尚达人"};
	        Map<String, Object> map;
	        for(int i = 0; i < 20; i++){
	        map = new HashMap<String, Object>();
	        map.put("img", R.drawable.lxia);
	        map.put("title", titleStr[i%6]);
	        map.put("date", "today");
	        map.put("info", "快乐源于生活...");
	        list.add(map);
	        }
	        
	        return list;
	    }
	 
	 public void startForum(View v) {      
			//Intent intent = new Intent (MainWeixin.this,ForumActivity.class);			
			//startActivity(intent);	
			//Toast.makeText(getApplicationContext(), "��¼�ɹ�", Toast.LENGTH_LONG).show();
		 Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
 		
   } 
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.btn_send:
			//send();
			break;
		case R.id.btn_back:
			finish();
			break;
		}
	}
	
	//ViewHolder静态类
    static class ViewHolder
    {
        public ImageView img;
        public TextView title;
        public TextView date;
        public TextView info;
    }
	 public class MyAdapter extends BaseAdapter
	    {   
	        private LayoutInflater mInflater = null;
	        List<Map<String, Object>> mlist;
	        private MyAdapter(Context context, List<Map<String, Object>> list)
	        {
	            //根据context上下文加载布局，这里的是Demo17Activity本身，即this
	            this.mInflater = LayoutInflater.from(context);
	            mlist = list;
	        }
	        @Override
	        public int getCount() {
	            //How many items are in the data set represented by this Adapter.
	            //在此适配器中所代表的数据集中的条目数
	            return data.size();
	        }
	        @Override
	        public Object getItem(int position) {
	            // Get the data item associated with the specified position in the data set.
	            //获取数据集中与指定索引对应的数据项
	            return position;
	        }
	        @Override
	        public long getItemId(int position) {
	            //Get the row id associated with the specified position in the list.
	            //获取在列表中与指定索引对应的行id
	            return position;
	        }
	                                                         
	        //Get a View that displays the data at the specified position in the data set.
	        //获取一个在数据集中指定索引的视图来显示数据
	        @Override
	        public View getView(int position, View convertView, ViewGroup parent) {
	            ViewHolder holder = null;
	            //如果缓存convertView为空，则需要创建View
	            if(convertView == null)
	            {
	                holder = new ViewHolder();
	                //根据自定义的Item布局加载布局
	                convertView = mInflater.inflate(R.layout.main_mei_list, null);
	                holder.img = (ImageView)convertView.findViewById(R.id.image);
	                holder.title = (TextView)convertView.findViewById(R.id.title);
	                holder.date =(TextView)convertView.findViewById(R.id.date);
	                holder.info = (TextView)convertView.findViewById(R.id.text);
	                //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
	                convertView.setTag(holder);
	            }else
	            {
	                holder = (ViewHolder)convertView.getTag();
	            }
	            holder.img.setBackgroundResource((Integer)mlist.get(position).get("img"));
	            holder.title.setText((String)mlist.get(position).get("title"));
	            holder.date.setText((String)mlist.get(position).get("date"));
	            holder.info.setText((String)mlist.get(position).get("info"));
	                                                             
	            return convertView;
	        }
	                                                         
	    }
	
    
}
