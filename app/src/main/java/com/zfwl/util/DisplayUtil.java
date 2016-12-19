package com.zfwl.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

/**
 * Android大小单位转换工具类
 */
public class DisplayUtil {

    /**
     * 获取屏幕宽度
     * @return 屏幕宽度
     */
    public static int getScreenWidth(){
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int spToPx(int sp){
        return (int) (sp * Resources.getSystem().getDisplayMetrics().density);
    }
    /**
     * dp转为px
     * @param dp dp值
     * @return px值
     */
    public static int dpToPx(int dp){
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    /**
     * px转为dp
     * @param px px值
     * @return dp值
     */
    public static int pxToDp(int px){
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     * 
     * @param spValue
     * @param fontScale
     *            （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int sp2px(float spValue, float fontScale) {
	return (int) (spValue * fontScale + 0.5f);
    }

    public static int getResDimen(Context context, int resId){
        TypedValue value = new TypedValue();
        context.getResources().getValue(resId, value, true);
        return (int)TypedValue.complexToFloat(value.data);
    }
}
