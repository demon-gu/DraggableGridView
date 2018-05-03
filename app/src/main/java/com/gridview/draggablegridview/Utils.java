package com.gridview.draggablegridview;
/*
 * @创建者     Demon
 * @创建时间   2018/5/2 16:33
 * @描述       工具类
 */

import android.content.Context;
import android.view.Display;
import android.view.WindowManager;

public class Utils {
    public static int getScreenWidth(Context context) {
        if (context == null){
            return 0;
        }
        Display display = ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int w = display.getWidth();
        return w;
    }
}
