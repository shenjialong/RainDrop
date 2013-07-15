package com.xiaoyudi.raindrop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.xiaoyudi.customadapter.GridViewAdapter;
import com.xiaoyudi.customview.NavigationBar;
import com.xiaoyudi.util.DeviceUtil;
import com.xiaoyudi.util.GridViewSettings;
import com.xiaoyudi.util.SoundPoolEngine;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DeviceUtil.init(this);
//		Log.i("sjl", "dip2px:"+DeviceUtil.dip2px(60));
		setContentView(R.layout.style1);
		soundPoolEngine = SoundPoolEngine.getInstance(this);
//		vibratorEngine = VibratorEngine.getInstance(this);
		init();
	}
	LinearLayout ll;
	NavigationBar navigationBar;
	GridView gridview_low;
	GridView gridview_up;
	GridViewSettings gvsettings;
	
	
	
	
	SoundPoolEngine soundPoolEngine;
//	VibratorEngine vibratorEngine;
	
//	以下数据供开发阶段 调试使用  正式使用的时候需要 从 array.xml文件中动态加载  by sjl 2013 07 12 
	int[] images={R.drawable.p1_11, R.drawable.p1_12, 
			 R.drawable.p1_13, R.drawable.p1_14, 
			 R.drawable.p1_15, R.drawable.p1_16, 
			 R.drawable.p1_17, R.drawable.p1_18,
			 R.drawable.p1_21, R.drawable.p1_22, 
			 R.drawable.p1_23, R.drawable.p1_24, 
			 R.drawable.p1_25, R.drawable.p1_26, 
			 R.drawable.p1_27, R.drawable.p1_28, 
			 R.drawable.p1_29, R.drawable.p1_31, 
			 R.drawable.p1_32, 
			 R.drawable.p1_33, R.drawable.p1_34, 
			 R.drawable.p1_35, R.drawable.p1_36};
	String [] texts;
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	public void init(){
		texts=getResources().getStringArray(R.array.daily_life);
		gridview_low=(GridView)findViewById(R.id.gridView_style1_low);
		gridview_up=(GridView)findViewById(R.id.gridView_style1_up);
		navigationBar=(NavigationBar)findViewById(R.id.navigationBar_style1);
		initGridView();
		initNavigationBar();
		
		int [] soundIds = {R.raw.s1_11, R.raw.s1_12, R.raw.s1_13, 
				R.raw.s1_14, R.raw.s1_15, R.raw.s1_16,
				R.raw.s1_17, R.raw.s1_18 ,
				
				R.raw.s1_21, R.raw.s1_22, R.raw.s1_23, 
				R.raw.s1_24, R.raw.s1_25, R.raw.s1_26,
				R.raw.s1_27, R.raw.s1_28, R.raw.s1_29 ,
				
				R.raw.s1_31, R.raw.s1_32, R.raw.s1_33, 
//				R.raw.s1_34, R.raw.s1_35, R.raw.s1_36 ,
//				
//				R.raw.s1_42, R.raw.s1_43, R.raw.s1_44, 
//				R.raw.s1_45, R.raw.s1_46, R.raw.s1_47, 
//				R.raw.s1_48, R.raw.s1_49,
//				
//				R.raw.s1_51, R.raw.s1_52, R.raw.s1_53, 
//				R.raw.s1_54, R.raw.s1_55, R.raw.s1_56 ,
//				
//				R.raw.s1_61, R.raw.s1_62, R.raw.s1_63, 
//				R.raw.s1_64, R.raw.s1_65, R.raw.s1_66 
				
		};
//		
		mSoundIdList = soundPoolEngine.loadRes(soundIds);
	}
	
	public void initSettings(){
		SharedPreferences layout_settings = getSharedPreferences("layout_settings", 0);
		int rows=layout_settings.getInt("rows", 4);
		int columns=layout_settings.getInt("columns", 4);
		int layers=layout_settings.getInt("layers", 1);
		gvsettings=new  GridViewSettings(columns,rows,layers);
	}
	@Override
	protected void onPause() {
		soundPoolEngine.release();
//		MobclickAgent.onPause(this);
		super.onPause();
	}
	public void initGridView(){
		//读取配置变量  布局设置参数
		
		String [] from ={"image","text"};
		int [] to ={R.id.imageView_item,R.id.textView_item};
		initSettings();
		if(gvsettings.getLayers()==2){
//			不可见  且不占用布局空间 
			gridview_up.setVisibility(View.VISIBLE);	
			gridview_up.setNumColumns(gvsettings.columns_layer_up);
			List <Map<String,Object>> dataSourceForLayerUp=getDataSourceForLayerUp();
			GridViewAdapter adapterForLayer2=new GridViewAdapter(MainActivity.this, dataSourceForLayerUp, 
					R.layout.gridviewitem, from, to,gvsettings,this.gridview_up, soundPoolEngine,mSoundIdList);
			
//			增加ITEM的监听器
			gridview_up.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
//					发声
					Log.i("sjl", "onItemClickListener");
					Toast.makeText(MainActivity.this,"position:"+position,Toast.LENGTH_LONG).show();
//					soundPoolEngine.play(mSoundIdList.get(position));
				}
			});
			
			gridview_up.setAdapter(adapterForLayer2);
			
			List <Map<String,Object>> dataSource=getDataSourceForLayer1(2);
			GridViewAdapter adapter=new GridViewAdapter(MainActivity.this, dataSource, R.layout.gridviewitem, 
					from, to,gvsettings,this.gridview_low, soundPoolEngine,mSoundIdList);
			gridview_low.setNumColumns(gvsettings.columns_layer_down);
			gridview_low.setAdapter(adapter);
			
		}else{
			List <Map<String,Object>> dataSource=getDataSourceForLayer1(1);
			GridViewAdapter adapter=new GridViewAdapter(MainActivity.this, dataSource, R.layout.gridviewitem, 
					from, to,gvsettings,this.gridview_low,soundPoolEngine,mSoundIdList);
			gridview_low.setNumColumns(gvsettings.columns);
			gridview_low.setAdapter(adapter);
			gridview_up.setVisibility(View.GONE);
		}
	}
	
	
	public List <Map<String,Object>> getDataSourceForLayerUp(){
		List <Map<String,Object>> datasource=new ArrayList<Map<String,Object>>();
		int totalItems=gvsettings.rows_layer_up*gvsettings.columns_layer_up;
		Map<String,Object> item;
		for(int i=0;i<totalItems;i++){
			item=new HashMap<String, Object>();
			item.put("image", images[i]);
			item.put("text", texts[i]);
			datasource.add(item);
		}
		return datasource;
	}
	
	public List <Map<String,Object>> getDataSourceForLayer1(int type){
		List <Map<String,Object>> datasource=new ArrayList<Map<String,Object>>();
		int totalItems;
		
		int begin;
		
		if(type==1){
			begin=0;
			totalItems=gvsettings.rows*gvsettings.columns;
		}else{
			begin=gvsettings.rows_layer_up*gvsettings.columns_layer_up;
			totalItems=gvsettings.rows_layer_down*gvsettings.columns_layer_down;
		}
		
		Map<String,Object> item;
		for(int i=0;i<totalItems;i++){
			item=new HashMap<String, Object>();
			item.put("image", images[i+begin]);
			item.put("text", texts[i+begin]);
			datasource.add(item);
		}
		return datasource;
	}
	
	
	
	public void initNavigationBar(){
		navigationBar.setTvTitle("小雨滴");
		navigationBar.setBtnRightClickListener(new  OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder=new  AlertDialog.Builder(MainActivity.this);
				builder.setTitle("选择切换的样式").setSingleChoiceItems(new String[]{"4*4布局","3*4布局","3*3布局","混合布局"}, 0
						, new AlertDialog.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
//								保存用户选择的布局设置参数
								SharedPreferences layout_settings = getSharedPreferences("layout_settings", 0);
								int rows=4;
								int columns=4;
								int layers=1;
								
								switch(which){
								case 0:
									rows=4;
									columns=4;
									break;
								case 1:
									rows=3;
									columns=4;
									break;
								case 2:
									rows=3;
									columns=3;
									break;
								case 3:
									layers=2;
									layout_settings.edit().putInt("layers", layers).commit();
									break;
								}
								
								Editor editor=layout_settings.edit();
								editor.putInt("columns", columns);
								editor.putInt("layers", layers);
								editor.putInt("rows", rows).commit();
								
								initGridView();
								dialog.dismiss();
								
							}
						}).show();
						
			}
		});
		
	}
//	测试阶段先放二十个
	ArrayList<Integer> mSoundIdList;
	@Override
	protected void onResume() {
		
			
//		MobclickAgent.onResume(this);
		super.onResume();
	}

}
