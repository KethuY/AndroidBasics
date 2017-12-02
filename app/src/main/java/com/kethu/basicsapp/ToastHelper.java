package com.kethu.basicsapp;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by satya on 02-Dec-17.
 */

public class ToastHelper {
    public static void showToast(Context context,String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }

    public  void showToastH(Context context,String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }
}
