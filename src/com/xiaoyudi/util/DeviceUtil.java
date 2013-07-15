package com.xiaoyudi.util;

import android.content.Context;
import android.util.DisplayMetrics;

public class DeviceUtil {
	static DisplayMetrics dm;

	public static void init(Context context) {
		if (dm == null) {
			dm = context.getResources().getDisplayMetrics();
		}
	}

	public static float dip2px(int dip) {
		return dip * dm.density + 0.5f;
	}
	
	public static float px2dip(float px) {
		return px / dm.density + 0.5f;
	}
}
