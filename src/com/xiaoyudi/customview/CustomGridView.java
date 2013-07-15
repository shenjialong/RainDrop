package com.xiaoyudi.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.GridView;

public class CustomGridView extends GridView{

		public CustomGridView(Context context) {
		super(context);
		}
		public CustomGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
		}

		public CustomGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		}
		@Override
		public boolean dispatchTouchEvent(MotionEvent ev) {
			if(ev.getAction() == MotionEvent.ACTION_MOVE){
			           return true;
			       }
			return super.dispatchTouchEvent(ev);
		}
}
