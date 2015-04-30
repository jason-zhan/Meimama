package com.meima.ui;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.meima.R;
import com.meima.ui.MyListView.OnRefreshListener;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainWeixin extends Activity {
	
	public static MainWeixin instance = null;
	 
	private ViewPager mTabPager;	
	private ImageView mTabImg;// 
	private ImageView mTab1,mTab2,mTab3,mTab4;
	private int zero = 0;// 
	private int currIndex = 0;// 
	private int one;//
	private int two;
	private int three;
	private LinearLayout mClose;
    private LinearLayout mCloseBtn;
    private View layout;	
	private boolean menu_display = false;
	private PopupWindow menuWindow;
	private LayoutInflater inflater;
	//private Button mRightBtn;
	
	private List<String> list;
	
	
	private ArrayList<HashMap<String, Object>>   listItems;    //存放文字、图片信息
    private SimpleAdapter listItemAdapter;           //适配器    

    private List<Map<String, Object>> data;
    private ListView lv;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_weixin);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN); 
        instance = this;
        
        SharedPreferences preferences = getSharedPreferences("share", Context.MODE_PRIVATE);
        Editor editor = preferences.edit();

        //editor.putBoolean("isFirstIn", false);
        // 提交修改
        //editor.commit();  
        /*
        mRightBtn = (Button) findViewById(R.id.right_btn);
        mRightBtn.setOnClickListener(new Button.OnClickListener()
		{	@Override
			public void onClick(View v)
			{	showPopupWindow (MainWeixin.this,mRightBtn);
			}
		  });*/
        
        mTabPager = (ViewPager)findViewById(R.id.tabpager);
        mTabPager.setOnPageChangeListener(new MyOnPageChangeListener());
        
        
        mTab1 = (ImageView) findViewById(R.id.img_weixin);
        mTab2 = (ImageView) findViewById(R.id.img_address);
        mTab3 = (ImageView) findViewById(R.id.img_friends);
        mTab4 = (ImageView) findViewById(R.id.img_settings);
        mTabImg = (ImageView) findViewById(R.id.img_tab_now);
        mTab1.setOnClickListener(new MyOnClickListener(0));
        mTab2.setOnClickListener(new MyOnClickListener(1));
        mTab3.setOnClickListener(new MyOnClickListener(2));
        mTab4.setOnClickListener(new MyOnClickListener(3));
        Display currDisplay = getWindowManager().getDefaultDisplay();
        int displayWidth = currDisplay.getWidth();
        int displayHeight = currDisplay.getHeight();
        one = displayWidth/4; //����ˮƽ����ƽ�ƴ�С
        two = one*2;
        three = one*3;
        
        LayoutInflater mLi = LayoutInflater.from(this);
        //View view1 = mLi.inflate(R.layout.main_tab_weixin, null);
        View view1 = mLi.inflate(R.layout.main_tab_meima, null);
        data = getData();
        MyAdapter adapter = new MyAdapter(this,data);
        lv = (ListView)view1.findViewById(R.id.meima_tab);
        lv.setAdapter(adapter);
        View view2 = mLi.inflate(R.layout.main_tab_address, null);
        View view3 = mLi.inflate(R.layout.main_tab_friends, null);
        View view4 = mLi.inflate(R.layout.main_tab_settings, null);
        
        final ArrayList<View> views = new ArrayList<View>();
        views.add(view1);
        views.add(view2);
        views.add(view3);
        views.add(view4);
        PagerAdapter mPagerAdapter = new PagerAdapter() {
			
			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}
			
			@Override
			public int getCount() {
				return views.size();
			}

			@Override
			public void destroyItem(View container, int position, Object object) {
				((ViewPager)container).removeView(views.get(position));
			}
			
			//@Override
			//public CharSequence getPageTitle(int position) {
				//return titles.get(position);
			//}
			
			@Override
			public Object instantiateItem(View container, int position) {
				((ViewPager)container).addView(views.get(position));
				return views.get(position);
			}
		};
		//initListView();
		//this.setListAdapter(listItemAdapter);  
		
		
		//adapter = new LvAdapter(iconList, this);
		//lv.setAdapter(adapter);
		mTabPager.setAdapter(mPagerAdapter);
    }
    private List<Map<String, Object>> getData()
    {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map;
        String [] titleStr = {"情感天空","新手报道","两性健康","美食厨房","幼儿园宝宝","时尚达人"};
        for(int i = 0; i < 6; i++) {
        map = new HashMap<String, Object>();
        map.put("img", R.drawable.lxia);
        map.put("title", titleStr[i]);
        map.put("date", "today");
        map.put("info", "快乐源于生活...");
        list.add(map);
        }
        
        return list;
    }
                  
    public class MyOnClickListener implements View.OnClickListener {
		private int index = 0;

		public MyOnClickListener(int i) {
			index = i;
		}
		@Override
		public void onClick(View v) {
			mTabPager.setCurrentItem(index);
		}
	};
    /**
     * 设置适配器内容
     */
    /*private void initListView()   {   
        listItems = new ArrayList<HashMap<String, Object>>();
        String[] mListTitle = { "时尚美妈", "情感天空", "美食厨房", "幼儿园宝宝","两性健康"};
        String[] mListStr = { "辣妈商场", "爱情是什么", "山美食", "宝宝怎么教育",
                "老公是什么" };
        int lengh = mListTitle.length;
        for(int i=0;i < lengh;i++)    {   
            HashMap<String, Object> map = new HashMap<String, Object>();   
            //map.put("ItemTitle", "Music： "+i);    //文字
            //map.put("ItemImage", R.drawable.xiaohei);   //图片 
            map.put("image", R.drawable.xiaohei);
            map.put("title", mListTitle[i]);
            map.put("text", mListStr[i]);
            listItems.add(map);   
        }   
        //生成适配器的Item和动态数组对应的元素   
        listItemAdapter = new SimpleAdapter(this,listItems,   // listItems数据源    
                R.layout.main_mei_list,  //ListItem的XML布局实现   
                new String[] {"image", "title","text"},     //动态数组与ImageItem对应的子项          
                new int[ ] {R.id.image,R.id.title,R.id.text}      //list_item.xml布局文件里面的一个ImageView的ID,一个TextView 的ID   
        );   
    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id)  {
        // TODO Auto-generated method stub
        Log.e("position", "" + position);
        setTitle("你点击第"+position+"行"); 
    }   
    class ClickEvent implements OnClickListener {
        @Override
        public void onClick (View v)  {
            // 向ListView里添加一项
            HashMap<String, Object> map = new HashMap<String, Object>();   
            map.put("ItemTitle", "Music： ");   
            map.put("ItemImage", R.drawable.xiaohei);     //每次都放入同样的图片资源ID
            listItems.add(map);
            //重新设置适配器
            MainWeixin.this.setListAdapter(listItemAdapter);
        }
    }

   */
	
    
	public class MyOnPageChangeListener implements OnPageChangeListener {
		@Override
		public void onPageSelected(int arg0) {
			Animation animation = null;
			switch (arg0) {
			case 0:
				mTab1.setImageDrawable(getResources().getDrawable(R.drawable.tab_weixin_pressed));
				if (currIndex == 1) {
					animation = new TranslateAnimation(one, 0, 0, 0);
					mTab2.setImageDrawable(getResources().getDrawable(R.drawable.tab_address_normal));
				} else if (currIndex == 2) {
					animation = new TranslateAnimation(two, 0, 0, 0);
					mTab3.setImageDrawable(getResources().getDrawable(R.drawable.tab_find_frd_normal));
				}
				else if (currIndex == 3) {
					animation = new TranslateAnimation(three, 0, 0, 0);
					mTab4.setImageDrawable(getResources().getDrawable(R.drawable.tab_settings_normal));
				}
				break;
			case 1:
				mTab2.setImageDrawable(getResources().getDrawable(R.drawable.tab_address_pressed));
				if (currIndex == 0) {
					animation = new TranslateAnimation(zero, one, 0, 0);
					mTab1.setImageDrawable(getResources().getDrawable(R.drawable.tab_weixin_normal));
				} else if (currIndex == 2) {
					animation = new TranslateAnimation(two, one, 0, 0);
					mTab3.setImageDrawable(getResources().getDrawable(R.drawable.tab_find_frd_normal));
				}
				else if (currIndex == 3) {
					animation = new TranslateAnimation(three, one, 0, 0);
					mTab4.setImageDrawable(getResources().getDrawable(R.drawable.tab_settings_normal));
				}
				break;
			case 2:
				mTab3.setImageDrawable(getResources().getDrawable(R.drawable.tab_find_frd_pressed));
				if (currIndex == 0) {
					animation = new TranslateAnimation(zero, two, 0, 0);
					mTab1.setImageDrawable(getResources().getDrawable(R.drawable.tab_weixin_normal));
				} else if (currIndex == 1) {
					animation = new TranslateAnimation(one, two, 0, 0);
					mTab2.setImageDrawable(getResources().getDrawable(R.drawable.tab_address_normal));
				}
				else if (currIndex == 3) {
					animation = new TranslateAnimation(three, two, 0, 0);
					mTab4.setImageDrawable(getResources().getDrawable(R.drawable.tab_settings_normal));
				}
				break;
			case 3:
				mTab4.setImageDrawable(getResources().getDrawable(R.drawable.tab_settings_pressed));
				if (currIndex == 0) {
					animation = new TranslateAnimation(zero, three, 0, 0);
					mTab1.setImageDrawable(getResources().getDrawable(R.drawable.tab_weixin_normal));
				} else if (currIndex == 1) {
					animation = new TranslateAnimation(one, three, 0, 0);
					mTab2.setImageDrawable(getResources().getDrawable(R.drawable.tab_address_normal));
				}
				else if (currIndex == 2) {
					animation = new TranslateAnimation(two, three, 0, 0);
					mTab3.setImageDrawable(getResources().getDrawable(R.drawable.tab_find_frd_normal));
				}
				break;
			}
			currIndex = arg0;
			animation.setFillAfter(true);
			animation.setDuration(150);
			mTabImg.startAnimation(animation);
		}
		
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
	}
	
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {  //��ȡ back��
    		
        	if(menu_display){         //��� Menu�Ѿ��� ���ȹر�Menu
        		menuWindow.dismiss();
        		menu_display = false;
        		}
        	else {
        		Intent intent = new Intent();
            	intent.setClass(MainWeixin.this,Exit.class);
            	startActivity(intent);
        	}
    	}
    	
    	else if(keyCode == KeyEvent.KEYCODE_MENU){   //��ȡ Menu��			
			if(!menu_display){
				inflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
				layout = inflater.inflate(R.layout.main_menu, null);
				
				menuWindow = new PopupWindow(layout,LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT); //������������width��height
				//menuWindow.showAsDropDown(layout); //���õ���Ч��
				//menuWindow.showAsDropDown(null, 0, layout.getHeight());
				menuWindow.showAtLocation(this.findViewById(R.id.mainweixin), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0); //����layout��PopupWindow����ʾ��λ��
				//��λ�ȡ����main�еĿؼ��أ�Ҳ�ܼ�
				mClose = (LinearLayout)layout.findViewById(R.id.menu_close);
				mCloseBtn = (LinearLayout)layout.findViewById(R.id.menu_close_btn);
				
				
				
				mCloseBtn.setOnClickListener (new View.OnClickListener() {					
					@Override
					public void onClick(View arg0) {						
						//Toast.makeText(Main.this, "�˳�", Toast.LENGTH_LONG).show();
						Intent intent = new Intent();
			        	intent.setClass(MainWeixin.this,Exit.class);
			        	startActivity(intent);
			        	menuWindow.dismiss(); //��Ӧ����¼�֮��ر�Menu
					}
				});				
				menu_display = true;				
			}else{
				//�����ǰ�Ѿ�Ϊ��ʾ״̬������������
				menuWindow.dismiss();
				menu_display = false;
				}
			
			return false;
		}
    	return false;
    }
	public void btnmainright(View v) {  
		Intent intent = new Intent (MainWeixin.this,MainTopRightDialog.class);			
		startActivity(intent);	
		//Toast.makeText(getApplicationContext(), "����˹��ܰ�ť", Toast.LENGTH_LONG).show();
      }  	
	public void startchat(View v) {      
		Intent intent = new Intent (MainWeixin.this,ForumActivity.class);			
		startActivity(intent);	
		//Toast.makeText(getApplicationContext(), "��¼�ɹ�", Toast.LENGTH_LONG).show();
      }  
	public void exit_settings(View v) {                           
		Intent intent = new Intent (MainWeixin.this,ExitFromSettings.class);			
		startActivity(intent);	
	 }
	public void btn_shake(View v) {                                   //�ֻ�ҡһҡ
		Intent intent = new Intent (MainWeixin.this,ShakeActivity.class);			
		startActivity(intent);	
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
    
    

