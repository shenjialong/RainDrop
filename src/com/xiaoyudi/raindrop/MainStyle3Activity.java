package com.xiaoyudi.raindrop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xiaoyudi.customview.NavigationBar;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;

public class MainStyle3Activity extends Activity {

	
	NavigationBar navigationBar;
	GridView gridview1;
	GridView gridview2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.style3);
		init();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main_style2, menu);
		return true;
	}
	
	public void init(){
		gridview1=(GridView)findViewById(R.id.gridView_2_style3);
		gridview2=(GridView)findViewById(R.id.gridView_1_style3);
		navigationBar=(NavigationBar)findViewById(R.id.navigationBar_style3);
		initGridView();
		initNavigationBar();
	}
	
	public void initGridView(){
		List <Map<String,Object>> datasource=new ArrayList<Map<String,Object>>();
		Map<String,Object> item=new HashMap<String, Object>();
		Map<String,Object> item2=new HashMap<String, Object>();
		Map<String,Object> item3=new HashMap<String, Object>();
		Map<String,Object> item4=new HashMap<String, Object>();
		Map<String,Object> item5=new HashMap<String, Object>();
		Map<String,Object> item6=new HashMap<String, Object>();
		Map<String,Object> item7=new HashMap<String, Object>();
		Map<String,Object> item8=new HashMap<String, Object>();
		Map<String,Object> item9=new HashMap<String, Object>();
		Map<String,Object> item10=new HashMap<String, Object>();
		Map<String,Object> item11=new HashMap<String, Object>();
		Map<String,Object> item12=new HashMap<String, Object>();
		Map<String,Object> item13=new HashMap<String, Object>();
		Map<String,Object> item14=new HashMap<String, Object>();
		item.put("image", R.drawable.p1_1);
		item.put("text", "drink");
		item2.put("image", R.drawable.p1_1);
		item2.put("text", "drink2");
		item3.put("image", R.drawable.p1_1);
		item3.put("text", "drink");
		item4.put("image", R.drawable.p1_1);
		item4.put("text", "drink2");
		item5.put("image", R.drawable.p1_11);
		item5.put("text", "drink");
		item6.put("image", R.drawable.p1_11);
		item6.put("text", "drink2");
		item7.put("image", R.drawable.p1_11);
		item7.put("text", "drink2");
		item8.put("image", R.drawable.p1_11);
		item8.put("text", "drink2");
		item9.put("image", R.drawable.p1_11);
		item9.put("text", "drink2");
		item10.put("image", R.drawable.p1_11);
		item10.put("text", "drink2");
		item11.put("image", R.drawable.p1_11);
		item11.put("text", "drink2");
		item12.put("image", R.drawable.p1_11);
		item12.put("text", "drink2");
		item13.put("image", R.drawable.p1_11);
		item13.put("text", "drink2");
		item14.put("image", R.drawable.p1_11);
		item14.put("text", "drink2");
		
		datasource.add(item2);
		datasource.add(item);
		datasource.add(item3);
		datasource.add(item4);
		datasource.add(item5);
		datasource.add(item6);
		datasource.add(item7);
		datasource.add(item8);
		datasource.add(item9);
		
		datasource.add(item10);
		datasource.add(item11);
		datasource.add(item12);
		datasource.add(item13);
		datasource.add(item14);
		
		SimpleAdapter adapter=new SimpleAdapter(MainStyle3Activity.this, datasource, 
				R.layout.gridviewitem, new String[]{"image","text"}, 
				new int[]{R.id.imageView_item,R.id.textView_item});
		this.gridview1.setAdapter(adapter);
		this.gridview2.setAdapter(adapter);
		
	}
	
	public void initNavigationBar(){
		navigationBar.setTvTitle("–°”ÍµŒ");
		navigationBar.setBtnLeftClickListener(new  OnClickListener() {
			@Override
			public void onClick(View v) {
				
			}
		});
		
	}

}
