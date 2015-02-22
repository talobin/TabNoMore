package com.haivo.util;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.ArrayRes;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntegerRes;
import android.support.annotation.PluralsRes;
import android.support.annotation.StringRes;
import android.util.TypedValue;
import com.haivo.HaiApp;
import com.noisepages.nettoyeur.R;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

public class ResHelper {

    public static Resources getResources() {
        return HaiApp.getInstance().getResources();
    }

    public static String getStringByName(String name) {
        int id =
            getResources().getIdentifier(name, "string", HaiApp.getInstance().getPackageName());
        return getStringByResId(id);
    }

    public static int getStringId(String name) {
        try {
            Class res = R.string.class;
            Field field = res.getField("stringName");
            return field.getInt(null);
        } catch (Exception e) {
            //LogUtil.logError(ResHelper.class, "Failure to get string id.", e);
            return 0;
        }
    }

    public static String getQuantityString(@PluralsRes int resId, int quantity, Object... args) {
        return getResources().getQuantityString(resId, quantity, args);
    }

    public static String getStringByResId(@StringRes int resId) {
        return HaiApp.getInstance().getString(resId);
    }

    public static String getStringByResId(@StringRes int resId, Object... args) {
        return HaiApp.getInstance().getString(resId, args);
    }

    public static String[] getStringArray(@ArrayRes int resId) {
        return getResources().getStringArray(resId);
    }

    public static int getInteger(@IntegerRes int resId) {
        return getResources().getInteger(resId);
    }

    public static int getDrawableId(String name) {

        try {
            Class res = R.drawable.class;
            Field field = res.getField(name);
            return field.getInt(null);
        } catch (Exception e) {
            //LogUtil.logError(ResHelper.class, "Failure to get drawable id.", e);
            return 0;
        }
    }

    public static Drawable getDrawableByName(String name) {
        return getDrawable(getDrawableId(name));
    }

    public static Drawable getDrawable(@DrawableRes int resId) {
        return getResources().getDrawable(resId);
    }

    /**
     * Retrieves the color resources given a color resource ID.
     *
     * @param colorResId the resource ID.
     * @return the Color.
     */
    public static int getColor(@ColorRes int colorResId) {
        return getResources().getColor(colorResId);
    }

    public static int getDimenPixelSize(@DimenRes int resId) {
        return getResources().getDimensionPixelOffset(resId);
    }

    public static int[] getIntArray(@ArrayRes int resId) {
        return getResources().getIntArray(resId);
    }

    /**
     * Retrieve ArrayList of String
     */
    public static ArrayList<String> getStringArrayList(@ArrayRes int resId) {
        return new ArrayList<String>(Arrays.asList(getStringArray(resId)));
    }

    public static float convertDpToPx(int value) {
        final Resources r = getResources();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, r.getDisplayMetrics());
    }
}
