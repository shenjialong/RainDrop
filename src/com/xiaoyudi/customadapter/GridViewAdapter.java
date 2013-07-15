package com.xiaoyudi.customadapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaoyudi.raindrop.R;
import com.xiaoyudi.util.GridViewSettings;
import com.xiaoyudi.util.SoundPoolEngine;

public class GridViewAdapter extends BaseAdapter {
	
	
	Context  context;
	List<Map<String,Object>> dataSource;
	String [] from;
	int[] to; 
	LayoutInflater layoutInflater;
	int resource;
	GridViewSettings gvsettings;
	int itemHeigh;
	GridView gridview;
//	测试阶段先放二十个
	ArrayList<Integer> mSoundIdList;
	SoundPoolEngine soundPoolEngine;
    
	public void initItemHeigh(){
		DisplayMetrics dm = new DisplayMetrics();   
		((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(dm);
		int heigh=dm.heightPixels;
		int rows=gvsettings.rows;
		this.itemHeigh=((heigh*3)/(rows*4));
	}
	
	public GridViewAdapter(Context context,List<Map <String,Object>> dataSource,int resource,String[] from,
			int [] to,GridViewSettings gvsettings,GridView gridview,SoundPoolEngine soundPoolEngine,ArrayList<Integer> mSoundIdList){
		this.mSoundIdList=mSoundIdList;
		this.soundPoolEngine=soundPoolEngine;
		this.context=context;
		this.dataSource=dataSource;
		this.from=from;
		this.to=to; 
		this.layoutInflater=LayoutInflater.from(context);
		this.resource=resource;
		this.gvsettings=gvsettings;
		this.gridview=gridview;
		initItemHeigh();
	}
	@Override
	public int getCount() {
		return dataSource.size();
	}

	@Override
	public Object getItem(int position) {
		return dataSource.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		
//		int pHight = parent.getHeight();
//		Log.i("sjl", "pHight:"+pHight);
//        LayoutParams params = new LayoutParams(
//                        LayoutParams.MATCH_PARENT,pHight/gvsettings.rows);
//        Log.i("sjl", "pHight/gvsettings.rows:"+pHight/gvsettings.rows);
//		parent.setLayoutParams(params);
		if(convertView==null){
			convertView=layoutInflater.inflate(this.resource, null);
			AbsListView.LayoutParams param = new AbsListView.LayoutParams(
	                android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
	                this.itemHeigh);
			Log.i("sjl", "itemHeigh"+itemHeigh);
	        convertView.setLayoutParams(param);
		}
		ImageView imageview=(ImageView)convertView.findViewById(to[0]);
		TextView textview=(TextView)convertView.findViewById(to[1]);
		
		imageview.setImageResource((Integer)dataSource.get(position).get(from[0]));
		textview.setText((String)dataSource.get(position).get(from[1]));
		
		imageview.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				soundPoolEngine.play(mSoundIdList.get(position));
			}
		});
		
		return convertView;
		
	}


}
